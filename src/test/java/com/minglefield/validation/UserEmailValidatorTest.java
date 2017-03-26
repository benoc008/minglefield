package com.minglefield.validation;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.minglefield.dao.UserDAO;
import com.minglefield.domain.User;
import com.minglefield.dto.RegisterUserCommand;
import com.minglefield.exception.MingleException;
import com.minglefield.matcher.MingleExceptionMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserEmailValidatorTest {

    private UserDAO userDAO = mock(UserDAO.class);
    private UserEmailValidator userEmailValidator = new UserEmailValidator(userDAO);

    @Before
    public void setUp() {
        when(userDAO.findByEmail(anyString())).thenReturn(null);
    }

    @Test
    public void requriesAnEmailAddress() {
        RegisterUserCommand command = new RegisterUserCommand();
        try {
            userEmailValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(UserEmailValidator.EMPTY_EMAIL));
        }
    }

    @Test
    public void doesntAllowEmptyEmailAddress() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.emailAddress = "";
        try {
            userEmailValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(UserEmailValidator.EMPTY_EMAIL));
        }
    }

    @Test
    public void doesntAllowInvalidEmailAddress() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.emailAddress = "example@wrong";
        try {
            userEmailValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(UserEmailValidator.INVALID_EMAIL));
        }
    }

    @Test
    public void doesntAllowEmailAddressThatHasBeenAlreadyUsed() {
        RegisterUserCommand command = new RegisterUserCommand();
        command.emailAddress = "already@in-use.com";
        when(userDAO.findByEmail(command.emailAddress)).thenReturn(new User());
        try {
            userEmailValidator.validate(command);
            fail();
        } catch (MingleException e) {
            assertThat(e, MingleExceptionMatcher.withMessageKey(UserEmailValidator.EXISTING_EMAIL));
        }
    }
}