package integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testwork.api.client.UserApiClient;
import org.testwork.mock.MockServer;

import java.io.IOException;
import java.time.Duration;

public class BaseIntegrationTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MockServer mockServer;
    protected UserApiClient apiClient;
    protected String appUrl = "http://localhost:3000";

    @BeforeEach
    public void setUp() throws IOException {
        mockServer = new MockServer();
        mockServer.start();

        apiClient = new UserApiClient(mockServer.getBaseUrl());

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
        if (mockServer != null) {
            mockServer.shutdown();
        }
    }

    protected void navigateTo(String path) {
        driver.get(appUrl + path);
    }
}
