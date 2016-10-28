package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 * FileValidatorException
 */
public class FileValidatorException extends Exception {
    public FileValidatorException(Exception e) {
        super(e);
    }

    public FileValidatorException(String message) {
        super(message);
    }
}
