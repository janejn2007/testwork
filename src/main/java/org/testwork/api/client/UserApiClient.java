package org.testwork.api.client;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String BASE_PATH = "/api/v1/users";
    private final String baseUrl;

    public UserApiClient(String baseUrl) {
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Base URL cannot be null or empty");
        }
        this.baseUrl = baseUrl;
    }

    public Response createUser(Map<String, Object> userData) {
        if (userData == null || userData.isEmpty()) {
            throw new IllegalArgumentException("userData cannot be null or empty");
        }

        return given()
                .baseUri(baseUrl)
                .contentType(io.restassured.http.ContentType.JSON)
                .body(userData)
                .when()
                .post(BASE_PATH)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public Response getUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }

        return given()
                .baseUri(baseUrl)
                .pathParam("id", userId)
                .when()
                .get(BASE_PATH + "/{id}")
                .then()
                .log().body()
                .extract()
                .response();
    }

    public Response deleteUser(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }

        return given()
                .baseUri(baseUrl)
                .when()
                .delete(BASE_PATH + "/" + userId)
                .then()
                .log().status()
                .extract()
                .response();
    }
}
