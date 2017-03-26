package com.minglefield.validation;

import com.minglefield.dao.UserDAO;
import com.minglefield.domain.User;
import com.minglefield.exception.MingleException;
import com.minglefield.dto.RegisterUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEmailValidator implements UserRegistrationValidator {

    public static final String EMPTY_EMAIL = "registration.empty.email";
    public static final String INVALID_EMAIL = "registration.invalid.email";
    public static final String EXISTING_EMAIL = "registraion.existing.email";

    private UserDAO userDAO;

    @Autowired
    public UserEmailValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void validate(RegisterUserCommand registerUserCommand) throws MingleException {
        if (registerUserCommand.emailAddress == null || registerUserCommand.emailAddress.isEmpty()) {
            throw new MingleException(EMPTY_EMAIL);
        }
        String[] emailParts = registerUserCommand.emailAddress.split("@");
        if (emailParts.length != 2 || emailParts[1].split("\\.").length != 2) {
            throw new MingleException(INVALID_EMAIL);
        }
        User user = userDAO.findByEmail(registerUserCommand.emailAddress);
        if (user != null) {
            throw new MingleException(EXISTING_EMAIL);
        }
    }
}
