package org.testwork.data.generator;

import org.testwork.models.User;

import java.util.UUID;

public class UserDataGenerator {
    public static User createUser(){
        return User.builder()
                .userName(generateUniqueName())
                .userEmail(generateUniqueEmail())
                .userId(generateUserId())
                .build();
    }
    public static String generateUniqueEmail() {
        return "user_" + generateRandomString( 5) + "@test.com";
    }

    public static String generateInvalidEmail() {
        return "user_" + generateRandomString( 5) + "test.com";
    }


    public static String generateUniqueName() {
        return "User_" + generateRandomString(7);
    }

    public static String generateNotUniqueName() {
        return "User_" + "NotUnique";
    }

    public static String generateUserId() {
        return generateRandomString(10);
    }

    public static String generateRandomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
