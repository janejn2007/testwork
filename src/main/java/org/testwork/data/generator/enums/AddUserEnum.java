package org.testwork.data.generator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.testwork.data.generator.UserDataGenerator;

@Getter
@ToString
@AllArgsConstructor
public enum AddUserEnum {
    TEST1(UserDataGenerator.generateUserId(),
            UserDataGenerator.generateUniqueName(),
            UserDataGenerator.generateUniqueEmail()),

    TEST2(UserDataGenerator.generateUserId(),
            UserDataGenerator.generateUniqueName(),
            UserDataGenerator.generateInvalidEmail()),

    TEST3(UserDataGenerator.generateUserId(),
            UserDataGenerator.generateNotUniqueName(),
            UserDataGenerator.generateUniqueEmail());

    private final String userId;
    private final String userName;
    private final String userEmail;
}
