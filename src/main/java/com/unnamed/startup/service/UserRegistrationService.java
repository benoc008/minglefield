package com.unnamed.startup.service;

import com.unnamed.startup.dao.UserDAO;
import com.unnamed.startup.domain.User;
import com.unnamed.startup.dto.RegisterUserCommand;
import com.unnamed.startup.exception.BusinessException;
import com.unnamed.startup.validation.CollectiveValidator;
import com.unnamed.startup.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRegistrationService {

    private UserDAO userDAO;
    private List<UserRegistrationValidator> validators;

    @Autowired
    public UserRegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired(required = false)
    public void setValidators(List<UserRegistrationValidator> validators) {
        this.validators = validators;
    }

    public void registerUser(RegisterUserCommand registerUserCommand) throws BusinessException {
        validate(registerUserCommand);
        User user = User.createFromRegisterCommand(registerUserCommand);
        userDAO.persist(user);
    }

    private void validate(RegisterUserCommand registerUserCommand) throws BusinessException {
        CollectiveValidator<RegisterUserCommand> collectiveValidator = new CollectiveValidator<>();
        for (UserRegistrationValidator validator : validators) {
            collectiveValidator.addValidator(validator);
        }
        collectiveValidator.validate(registerUserCommand).throwExceptions();
    }
}
