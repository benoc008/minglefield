package com.minglefield.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MingleExceptionTest {

    private static final String MESSAGE_KEY = "message.key";
    private static final String PARAM_1 = "PARAM1";
    private static final String PARAM_2 = "PARAM2";
    private static final String PARAM_3 = "PARAM3";

    private MingleException mingleException;

    @Test
    public void printsMessageKey() {
        mingleException = new MingleException(MESSAGE_KEY);
        String printedBusinessException = mingleException.toString();
        String expectedMessage = "MingleException, message descriptions: [" + MESSAGE_KEY + "]";
        assertThat(printedBusinessException, is(expectedMessage));
    }

    @Test
    public void printsParametersAlongWithMessageKey() {
        MingleExceptionDescription mingleExceptionDescription = new MingleExceptionDescription(MESSAGE_KEY);
        mingleExceptionDescription.addParameters(PARAM_1, PARAM_2, PARAM_3);
        mingleException = new MingleException(mingleExceptionDescription);
        String printedBusinessException = mingleException.toString();
        String expectedMessage = "MingleException, message descriptions: [" + MESSAGE_KEY + ", " + PARAM_1 + ", " + PARAM_2 + ", " + PARAM_3 + "]";
        assertThat(printedBusinessException, is(expectedMessage));
    }
}