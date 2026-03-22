package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testwork.mock.MockServer;

import java.io.IOException;

public class BaseUITest {
    protected WebDriver driver;
    protected MockServer mockServer;

    @BeforeEach
    public void setUp() throws IOException {
        mockServer = new MockServer();
        mockServer.start();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
}
