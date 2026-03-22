package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testwork.data.generator.UserDataGenerator;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserApiTests extends BaseApiTest{
    @Test
    public void testCreateUserSuccess() {
        String userId = UserDataGenerator.generateUserId();
        String userName = UserDataGenerator.generateUniqueName();
        String userEmail = UserDataGenerator.generateUniqueEmail();

        mockServer.mockCreateUserSuccess(userId, userName, userEmail);

        Map<String, Object> userData = Map.of(
                "name", userName,
                "email", userEmail
        );

        Response response = apiClient.createUser(userData);

        assertEquals(201, response.getStatusCode());
        assertEquals(userId, response.jsonPath().getString("id"));
        assertEquals(userName, response.jsonPath().getString("name"));
        assertEquals(userEmail, response.jsonPath().getString("email"));
    }

    @Test
    public void testGetUserByIdSuccess() {
        String userId = UserDataGenerator.generateUserId();
        String userName = UserDataGenerator.generateUniqueName();
        String userEmail = UserDataGenerator.generateUniqueEmail();

        mockServer.mockGetUserSuccess(userId, userName, userEmail);

        Response response = apiClient.getUserById(userId);

        assertEquals(200, response.getStatusCode());
        assertEquals(userId, response.jsonPath().getString("id"));
        assertEquals(userName, response.jsonPath().getString("name"));
        assertEquals(userEmail, response.jsonPath().getString("email"));
    }

    @Test
    public void testDeleteUserSuccess() {
        // Генерируем уникальный ID
        String userId = UserDataGenerator.generateUserId();

        // Настраиваем мок
        mockServer.mockDeleteUserSuccess();

        // Выполняем запрос
        Response response = apiClient.deleteUser(userId);

        // Проверяем результат
        assertEquals(204, response.getStatusCode());
    }
}
