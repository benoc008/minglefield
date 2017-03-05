package com.unnamed.startup.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessException extends Exception {

    private List<BusinessExceptionDescription> businessExceptionDescriptions = new ArrayList<>();

    public BusinessException() {
        // default constructor
    }

    public BusinessException(List<BusinessExceptionDescription> businessExceptionDescriptions) {
        this.businessExceptionDescriptions = businessExceptionDescriptions;
    }

    public BusinessException(BusinessExceptionDescription... businessExceptionDescription) {
        businessExceptionDescriptions.addAll(Arrays.asList(businessExceptionDescription));
    }

    public BusinessException(String messageKey) {
        this(new BusinessExceptionDescription(messageKey));
    }

    public void addMessageDescription(BusinessExceptionDescription businessExceptionDescription) {
        businessExceptionDescriptions.add(businessExceptionDescription);
    }

    public List<BusinessExceptionDescription> getBusinessExceptionDescriptions() {
        return businessExceptionDescriptions;
    }

    public static void throwCollectiveBusinessException(List<BusinessException> businessExceptions) throws BusinessException {
        List<BusinessExceptionDescription> descriptions = new ArrayList<>();
        for (BusinessException businessException : businessExceptions) {
            for (BusinessExceptionDescription description : businessException.getBusinessExceptionDescriptions()) {
                descriptions.add(description);
            }
        }
        throw new BusinessException(descriptions);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", message descriptions: " + String.join(", ", businessExceptionDescriptions.toString());
    }
}
