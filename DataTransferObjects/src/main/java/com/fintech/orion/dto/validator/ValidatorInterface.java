package com.fintech.orion.dto.validator;

/**
 * Created by TharinduMP on 10/12/2016.
 * The Validator Interface
 * The interface provides 2 types of validation levels
 * 1. validations that are critical can be handled by Exception throwing
 * 2. validations that are non-critical can be returned as T for further actions.
 */
@FunctionalInterface
public interface ValidatorInterface<T extends ValidatorResult> {
    T validate(Object o) throws ValidatorException;
}
