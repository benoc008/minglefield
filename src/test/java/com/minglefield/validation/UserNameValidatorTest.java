package com.minglefield.validation;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.minglefield.dto.RegisterUserCommand;
import com.minglefield.exception.MingleException;
import com.minglefield.matcher.MingleExceptionMatcher;
import org.junit.Test;

public class UserNameValidatorTest {

    private UserNameValidator userNameValidator = new UserNameValidator();

    @Test
    public void doesntAllowEmptyFirstName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e,
                    MingleExceptionMatcher.withMessageKey(UserNameValidator.EMPTY_FIRST_NAME));
        }
    }

    @Test
    public void requiresAFirstName() {
        try {
            userNameValidator.validate(new RegisterUserCommand());
            fail();
        } catch (MingleException e) {
            assertThat(e,
                    MingleExceptionMatcher.withMessageKey(UserNameValidator.EMPTY_FIRST_NAME));
        }
    }

    @Test
    public void doesntAllowEmptyLastName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "Bob";
        command.lastName = "";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e,
                    MingleExceptionMatcher.withMessageKey(UserNameValidator.EMPTY_LAST_NAME));
        }
    }

    @Test
    public void requiresALastName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "Bob";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e,
                    MingleExceptionMatcher.withMessageKey(UserNameValidator.EMPTY_LAST_NAME));
        }
    }

    @Test
    public void doesntAllowLongFirstName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "qwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnm";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e,
                    MingleExceptionMatcher
                            .withMessageKey(UserNameValidator.INVALID_LENGTH_FIRST_NAME));
        }
    }

    @Test
    public void doesntAllowLongLastName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "bob";
        command.lastName = "qwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnm";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher
                    .withMessageKey(UserNameValidator.INVALID_LENGTH_LAST_NAME));
        }
    }

    @Test
    public void allowsOnlyLettersInFirstName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "bob123";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher
                    .withMessageKey(UserNameValidator.INVALID_FIRST_NAME));
        }
    }

    @Test
    public void allowsOnlyLettersInLastName() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.firstName = "bob";
        command.lastName = "name123";
        try {
            userNameValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher
                    .withMessageKey(UserNameValidator.INVALID_LAST_NAME));
        }
    }
}