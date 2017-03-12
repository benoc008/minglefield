package com.minglefield.validation;

import com.minglefield.dao.UserDAO;
import com.minglefield.domain.User;
import com.minglefield.exception.BusinessException;
import com.minglefield.dto.RegisterUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEmailValidator implements UserRegistrationValidator {

    private UserDAO userDAO;

    @Autowired
    public UserEmailValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void validate(RegisterUserCommand registerUserCommand) throws BusinessException {
        if (registerUserCommand.emailAddress == null || registerUserCommand.emailAddress.isEmpty()) {
            throw new BusinessException("registration.empty.email");
        }
        String[] emailParts = registerUserCommand.emailAddress.split("@");
        if (emailParts.length != 2 || emailParts[1].split(".").length != 2) {
            throw new BusinessException("registration.invalid.email");
        }
        User user = userDAO.findByEmail(registerUserCommand.emailAddress);
        if (user != null) {
            throw new BusinessException("registraion.existing.email");
        }
    }
}
