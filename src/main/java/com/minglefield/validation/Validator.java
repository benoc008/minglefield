package com.minglefield.validation;

import com.minglefield.exception.MingleException;

public interface Validator<T> {
    void validate(T object) throws MingleException;
}
