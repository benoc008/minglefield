package com.minglefield.validation;

import com.minglefield.exception.BusinessException;
import com.minglefield.dto.RegisterUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserNameValidator implements UserRegistrationValidator {

    private static final int NAME_MAX_LENGTH = 50;

    @Override
    public void validate(RegisterUserCommand registerUserCommand) throws BusinessException {

        if (registerUserCommand.firstName == null || registerUserCommand.firstName.isEmpty()) {
            throw new BusinessException("registration.empty.firstName");
        }
        if (registerUserCommand.firstName.length() > NAME_MAX_LENGTH) {
            throw new BusinessException("registration.lengthy.firstName");
        }
        if (registerUserCommand.lastName == null || registerUserCommand.lastName.isEmpty()) {
            throw new BusinessException("registration.empty.lastName");
        }
        if (registerUserCommand.lastName.length() > NAME_MAX_LENGTH) {
            throw new BusinessException("registration.lengthy.lastName");
        }
    }
}
