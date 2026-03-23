package integration;

import io.restassured.response.Response;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testwork.data.generator.UserDataGenerator;
import org.testwork.data.generator.enums.AddUserEnum;
import org.testwork.models.User;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserLifecycleTest extends BaseIntegrationTest {
    @ParameterizedTest
    @EnumSource(AddUserEnum.class)
    public void testUserLifecycle(AddUserEnum addUserEnum) {
        //Генерим пользователя
        User user = UserDataGenerator.createUser();

        mockServer.setupMockForUserCreation(addUserEnum);
        Map<String, Object> userData = Map.of(
                "name", user.getUserName(),
                "email", user.getUserEmail()
        );

        Response createResponse = apiClient.createUser(userData);

        assertEquals(201, createResponse.getStatusCode());
        assertEquals(user.getUserId(), createResponse.jsonPath().getString("id"));
        assertEquals(user.getUserName(), createResponse.jsonPath().getString("name"));
        assertEquals(user.getUserEmail(), createResponse.jsonPath().getString("email"));

        //Проверяем, что пользователь появился на странице /admin/users/{id}
        mockServer.mockGetUserSuccess(user.getUserId(), user.getUserName(), user.getUserEmail());

        navigateTo("/admin/users/" + user.getUserId());

        //Проверяем, что данные пользователя отображаются
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        assertEquals(user.getUserName(), driver.findElement(By.id("user-name")).getText());
        assertEquals(user.getUserEmail(), driver.findElement(By.id("user-email")).getText());
        assertEquals(user.getUserId(), driver.findElement(By.id("user-id")).getText());

        // удаление
        mockServer.mockDeleteUserSuccess();
        Response deleteResponse = apiClient.deleteUser(user.getUserId());
        assertEquals(204, deleteResponse.getStatusCode());
    }
}
