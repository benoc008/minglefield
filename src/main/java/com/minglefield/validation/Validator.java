package com.minglefield.validation;

import com.minglefield.exception.BusinessException;

public interface Validator<T> {
    void validate(T object) throws BusinessException;
}
