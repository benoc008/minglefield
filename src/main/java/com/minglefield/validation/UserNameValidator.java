package com.minglefield.validation;

import com.minglefield.dto.RegisterUserCommand;
import com.minglefield.exception.MingleException;
import org.springframework.stereotype.Component;

@Component
public class UserNameValidator implements UserRegistrationValidator {

    private static final int NAME_MAX_LENGTH = 100;

    public static final String EMPTY_FIRST_NAME = "registration.empty.firstName";
    public static final String INVALID_LENGTH_FIRST_NAME = "registration.lengthy.firstName";
    public static final String EMPTY_LAST_NAME = "registration.empty.lastName";
    public static final String INVALID_LENGTH_LAST_NAME = "registration.lengthy.lastName";
    public static final String INVALID_FIRST_NAME = "registration.invalid.firstName";
    public static final String INVALID_LAST_NAME = "registration.invalid.lastName";

    @Override
    public void validate(RegisterUserCommand registerUserCommand) throws MingleException {

        if (registerUserCommand.firstName == null || registerUserCommand.firstName.isEmpty()) {
            throw new MingleException(EMPTY_FIRST_NAME);
        }
        if (registerUserCommand.firstName.length() > NAME_MAX_LENGTH) {
            throw new MingleException(INVALID_LENGTH_FIRST_NAME);
        }
        if (hasInvalidCharacters(registerUserCommand.firstName)) {
            throw new MingleException(INVALID_FIRST_NAME);
        }
        if (registerUserCommand.lastName == null || registerUserCommand.lastName.isEmpty()) {
            throw new MingleException(EMPTY_LAST_NAME);
        }
        if (registerUserCommand.lastName.length() > NAME_MAX_LENGTH) {
            throw new MingleException(INVALID_LENGTH_LAST_NAME);
        }
        if (hasInvalidCharacters(registerUserCommand.lastName)) {
            throw new MingleException(INVALID_LAST_NAME);
        }
    }

    private boolean hasInvalidCharacters(String name) {
        return !name.matches("[a-zA-Z]+");
    }
}
