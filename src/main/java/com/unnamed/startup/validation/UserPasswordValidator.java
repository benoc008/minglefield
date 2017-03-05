package com.unnamed.startup.validation;

import com.unnamed.startup.dto.RegisterUserCommand;
import com.unnamed.startup.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordValidator implements UserRegistrationValidator {

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 100;

    @Override
    public void validate(RegisterUserCommand registerUserCommand) throws BusinessException {
        if (registerUserCommand.password == null || registerUserCommand.password.isEmpty()) {
            throw new BusinessException("registration.empty.password");
        }
        if (registerUserCommand.password.length() < PASSWORD_MIN_LENGTH ||
                registerUserCommand.password.length() > PASSWORD_MAX_LENGTH) {
            throw new BusinessException("registration.lengthy.password");
        }
    }
}
