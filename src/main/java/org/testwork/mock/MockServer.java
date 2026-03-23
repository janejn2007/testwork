package org.testwork.mock;


import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.testwork.data.generator.enums.AddUserEnum;

import java.io.IOException;

public class MockServer {
    private final MockWebServer mockWebServer;
    private String baseUrl;

    public MockServer() {
        this.mockWebServer = new MockWebServer();
    }

    public void start() throws IOException {
        mockWebServer.start();
        baseUrl = mockWebServer.url("").toString();
    }

    public void shutdown() throws IOException {
        mockWebServer.shutdown();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void mockCreateUserSuccess(String userId, String name, String email) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(String.format(
                        "{\"id\":\"%s\",\"name\":\"%s\",\"email\":\"%s\"}",
                        userId, name, email))
                .setHeader("Content-Type", "application/json"));
    }

    public void mockGetUserSuccess(String userId, String name, String email) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(String.format(
                        "{\"id\":\"%s\",\"name\":\"%s\",\"email\":\"%s\"}",
                        userId, name, email))
                .setHeader("Content-Type", "application/json"));
    }

    public void mockDeleteUserSuccess() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(204));
    }

    public void mockCreateUserWithDuplicateName(String name) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(String.format(
                        "{\"status\":400,\"error\":\"Bad Request\",\"message\":\"User name '%s' is already taken\",\"field\":\"name\"}",
                        name))
                .setHeader("Content-Type", "application/json"));
    }

    public void mockCreateUserWithInvalidEmail(String email) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(422)
                .setBody(String.format(
                        "{\"status\":422,\"error\":\"Validation Error\",\"errors\":[{\"field\":\"email\",\"message\":\"must be a valid email address\",\"value\":\"%s\"}]}",
                        email))
                .setHeader("Content-Type", "application/json"));
    }

    public void setupMockForUserCreation(AddUserEnum testData) {
        switch (testData) {
            case TEST1:
                mockCreateUserSuccess(testData.getUserId(),
                        testData.getUserName(),
                        testData.getUserEmail());
                break;

            case TEST2:
                // Невалидный email
                mockCreateUserWithInvalidEmail(testData.getUserEmail());
                break;

            case TEST3:
                // Неуникальное имя
                mockCreateUserWithDuplicateName(testData.getUserName());
                break;

            default:
                throw new IllegalArgumentException("Unknown test data: " + testData);
        }
    }
}
