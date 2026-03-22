package api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testwork.api.client.UserApiClient;
import org.testwork.mock.MockServer;

import java.io.IOException;

public class BaseApiTest {
    protected MockServer mockServer;
    protected UserApiClient apiClient;

    @BeforeAll
    public void setUp() throws IOException {
        mockServer = new MockServer();
        mockServer.start();
        apiClient = new UserApiClient(mockServer.getBaseUrl());
    }

    @AfterAll
    public void tearDown() throws IOException {
        if (mockServer != null) {
            mockServer.shutdown();
        }
    }
}
