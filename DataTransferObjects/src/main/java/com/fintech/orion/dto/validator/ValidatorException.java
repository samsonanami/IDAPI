package com.fintech.orion.dto.validator;

/**
 * Created by TharinduMP on 10/6/2016.
 * Main Validator Exception
 */
public class ValidatorException extends Exception {
    public ValidatorException(Exception e) {
        super(e);
    }

    public ValidatorException(String message) {
        super(message);
    }
}
