package org.testwork.mock;


import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

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
}
