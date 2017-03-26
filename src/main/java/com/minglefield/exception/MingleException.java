package com.minglefield.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MingleException extends Exception {

    private List<MingleExceptionDescription> mingleExceptionDescriptions = new ArrayList<>();

    public MingleException() {
        // default constructor
    }

    public MingleException(List<MingleExceptionDescription> mingleExceptionDescriptions) {
        this.mingleExceptionDescriptions = mingleExceptionDescriptions;
    }

    public MingleException(MingleExceptionDescription... mingleExceptionDescription) {
        mingleExceptionDescriptions.addAll(Arrays.asList(mingleExceptionDescription));
    }

    public MingleException(String messageKey) {
        this(new MingleExceptionDescription(messageKey));
    }

    public void addMessageDescription(MingleExceptionDescription mingleExceptionDescription) {
        mingleExceptionDescriptions.add(mingleExceptionDescription);
    }

    public List<MingleExceptionDescription> getMingleExceptionDescriptions() {
        return mingleExceptionDescriptions;
    }

    public static void throwCollectiveException(List<MingleException> mingleExceptions) throws MingleException {
        List<MingleExceptionDescription> descriptions = new ArrayList<>();
        for (MingleException mingleException : mingleExceptions) {
            for (MingleExceptionDescription description : mingleException.getMingleExceptionDescriptions()) {
                descriptions.add(description);
            }
        }
        throw new MingleException(descriptions);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", message descriptions: " + String.join(", ", mingleExceptionDescriptions
                .toString());
    }


}
