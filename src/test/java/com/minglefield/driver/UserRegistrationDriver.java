package com.minglefield.driver;

import static org.junit.Assert.assertTrue;

import com.minglefield.controller.UserRestController;
import com.minglefield.dto.RegisterUserCommand;
import com.minglefield.exception.MingleException;
import java.util.ArrayList;

public class UserRegistrationDriver {

    private UserRestController controller;
    private RegisterUserCommand command;
    private final ArrayList<MingleException> errors = new ArrayList<>();

    public UserRegistrationDriver(UserRestController controller) {
        this.controller = controller;
    }

    public UserRegistrationDriver clickTheRegisterANewAccountButton() {
        command = new RegisterUserCommand();
        return this;
    }

    public UserRegistrationDriver fillInFirstName(String firstName) {
        command.firstName = firstName;
        return this;
    }

    public UserRegistrationDriver fillInLastName(String lastName) {
        command.lastName = lastName;
        return this;
    }

    public UserRegistrationDriver fillInEmailAddress(String emailAddress) {
        command.emailAddress = emailAddress;
        return this;
    }

    public UserRegistrationDriver fillInPassword(String password) {
        command.password = password;
        return this;
    }

    public UserRegistrationDriver clickTheRegisterButton() {
        try {
            controller.registerUser(command);
        } catch (MingleException e) {
            errors.add(e);
        }
        return this;
    }

    public UserRegistrationDriver thereAreNoErrors() {
        assertTrue(errors.isEmpty());
        return this;
    }
}
