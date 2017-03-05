package com.unnamed.startup.validation;

import com.unnamed.startup.exception.BusinessException;

public interface Validator<T> {
    void validate(T object) throws BusinessException;
}
