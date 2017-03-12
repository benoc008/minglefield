package com.minglefield.validation;

import com.minglefield.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class CollectiveValidator<T> {

    private List<BusinessException> businessExceptions = new ArrayList<>();
    private List<Validator<T>> validators = new ArrayList<>();

    public CollectiveValidator() {
        // defaulct constructor
    }

    public CollectiveValidator(List<Validator<T>> validators) {
        this.validators = validators;
    }

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public <N extends T> CollectiveValidator<T> validate(N objectToValidate) {
        for (Validator validator : validators) {
            try {
                validator.validate(objectToValidate);
            } catch (BusinessException e) {
                businessExceptions.add(e);
            }
        }
        return this;
    }

    public void throwExceptions() throws BusinessException {
        BusinessException.throwCollectiveBusinessException(businessExceptions);
    }
}
