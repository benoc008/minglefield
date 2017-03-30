package com.minglefield.matcher;

import static org.hamcrest.Matchers.is;

import com.minglefield.domain.User;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsAnything;

public class UserMatcher extends TypeSafeDiagnosingMatcher<User> {

    private Matcher<Long> id = new IsAnything<>();
    private Matcher<String> firstName = new IsAnything<>();
    private Matcher<String> lastName = new IsAnything<>();
    private Matcher<String> emailAddress = new IsAnything<>();
    private Matcher<String> password = new IsAnything<>();

    public UserMatcher() {
        // default constructor
    }

    public static UserMatcher user() {
        return new UserMatcher();
    }

    public UserMatcher withId(Long id) {
        this.id = is(id);
        return this;
    }

    public UserMatcher withFirstName(String firstName) {
        this.firstName = is(firstName);
        return this;
    }

    public UserMatcher withLastName(String lastName) {
        this.lastName = is(lastName);
        return this;
    }

    public UserMatcher withEmailAddressName(String emailAddressName) {
        this.emailAddress = is(emailAddressName);
        return this;
    }

    public UserMatcher withPassword(String password) {
        this.password = is(password);
        return this;
    }

    @Override
    protected boolean matchesSafely(User user, Description description) {
        description
                .appendText(" id was: ").appendValue(user.getId())
                .appendText(" firstName was: ").appendValue(user.getFirstName())
                .appendText(" lastName was: ").appendValue(user.getLastName())
                .appendText(" emailAddress was: ").appendValue(user.getEmailAddress())
                .appendText(" password was: ").appendValue(user.getPassword());

        return this.id.matches(user.getId()) &&
                this.firstName.matches(user.getFirstName()) &&
                this.lastName.matches(user.getLastName()) &&
                this.emailAddress.matches(user.getEmailAddress()) &&
                this.password.matches(user.getPassword());
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" User with id: ").appendValue(id)
                .appendText(", firstName: ").appendValue(firstName)
                .appendText(", lastName: ").appendValue(lastName)
                .appendText(", emailAddress: ").appendValue(emailAddress)
                .appendText(", password: ").appendValue(password);
    }
}
