package com.minglefield.matcher;

import com.minglefield.exception.MingleException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class MingleExceptionMatcher extends TypeSafeDiagnosingMatcher<MingleException> {

    private MingleException exception;

    public static MingleExceptionMatcher withMessageKey(String messageKey) {
        return new MingleExceptionMatcher(new MingleException(messageKey));
    }

    private MingleExceptionMatcher(MingleException exception) {
        this.exception = exception;
    }

    @Override
    protected boolean matchesSafely(MingleException e, Description description) {
        description.appendText(" was ").appendValue(e);
        return exception.toString().equals(e.toString());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("exception should be ").appendValue(exception);
    }
}
