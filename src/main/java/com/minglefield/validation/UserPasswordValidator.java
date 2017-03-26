package com.minglefield.validation;

import com.minglefield.dto.RegisterUserCommand;
import com.minglefield.exception.MingleException;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordValidator implements UserRegistrationValidator {

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 100;

    public static final String PASSWORD_INVALID_LENGTH = "registration.lengthy.password";
    public static final String MISSING_PASSWROD = "registration.empty.password";

    @Override
    public void validate(RegisterUserCommand registerUserCommand) throws MingleException {
        if (registerUserCommand.password == null || registerUserCommand.password.isEmpty()) {
            throw new MingleException(MISSING_PASSWROD);
        }
        if (registerUserCommand.password.length() < PASSWORD_MIN_LENGTH ||
                registerUserCommand.password.length() > PASSWORD_MAX_LENGTH) {
            throw new MingleException(PASSWORD_INVALID_LENGTH);
        }
    }
}
