package com.unnamed.startup.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessExceptionDescription {

    private String messageKey;
    private String translatedMessage;
    private List<String> parameterList = new ArrayList<>();

    public BusinessExceptionDescription(String messageKey) {
        this.messageKey = messageKey;
        // TODO Create an object that can retrieve values from properties based on the key
        setTranslatedMessage(messageKey);
    }

    public void addParameters(String... parameters) {
        parameterList.addAll(Arrays.asList(parameters));
    }

    public void setTranslatedMessage(String translatedMessage) {
        this.translatedMessage = translatedMessage;
    }

    @Override
    public String toString() {
        if (parameterList.isEmpty()) {
            return messageKey;
        } else {
            return messageKey + ", " + String.join(", ", parameterList);
        }
    }
}
