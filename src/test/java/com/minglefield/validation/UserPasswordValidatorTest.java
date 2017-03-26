package com.minglefield.validation;

import static com.minglefield.validation.UserPasswordValidator.MISSING_PASSWROD;
import static com.minglefield.validation.UserPasswordValidator.PASSWORD_INVALID_LENGTH;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.minglefield.dto.RegisterUserCommand;
import com.minglefield.exception.MingleException;
import com.minglefield.matcher.MingleExceptionMatcher;
import org.junit.Test;

public class UserPasswordValidatorTest {

    private UserPasswordValidator userPasswordValidator = new UserPasswordValidator();

    @Test
    public void doesntAllowShortPassword() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.password = "abc123";
        try {
            userPasswordValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(PASSWORD_INVALID_LENGTH));
        }
    }

    @Test
    public void doesntAllowLongPassword() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.password = "qwertzuiopasdfghjklyxcvbnm0123456789qwertzuiopasdfghjklyxcvbnm0123456789qwertzuiopasdfghjklyxcvbnm0123456789qwertzuiopasdfghjklyxcvbnm0123456789";
        try {
            userPasswordValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(PASSWORD_INVALID_LENGTH));
        }
    }

    @Test
    public void doesntAllowEmptyPassword() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.password = "";
        try {
            userPasswordValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(MISSING_PASSWROD));
        }
    }

    @Test
    public void requiresAPassword() {
        try {
            userPasswordValidator.validate(new RegisterUserCommand());
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(MISSING_PASSWROD));
        }
    }
}