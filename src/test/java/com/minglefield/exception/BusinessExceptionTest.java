package com.minglefield.exception;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BusinessExceptionTest {

    private static final String MESSAGE_KEY = "message.key";
    private static final String PARAM_1 = "PARAM1";
    private static final String PARAM_2 = "PARAM2";
    private static final String PARAM_3 = "PARAM3";

    private BusinessException businessException;

    @Test
    public void printsMessageKey() {
        businessException = new BusinessException(MESSAGE_KEY);
        String printedBusinessException = businessException.toString();
        String expectedMessage = "BusinessException, message descriptions: [" + MESSAGE_KEY + "]";
        assertThat(printedBusinessException, is(expectedMessage));
    }

    @Test
    public void printsParametersAlongWithMessageKey() {
        BusinessExceptionDescription businessExceptionDescription = new BusinessExceptionDescription(MESSAGE_KEY);
        businessExceptionDescription.addParameters(PARAM_1, PARAM_2, PARAM_3);
        businessException = new BusinessException(businessExceptionDescription);
        String printedBusinessException = businessException.toString();
        String expectedMessage = "BusinessException, message descriptions: [" + MESSAGE_KEY + ", " + PARAM_1 + ", " + PARAM_2 + ", " + PARAM_3 + "]";
        assertThat(printedBusinessException, is(expectedMessage));
    }
}