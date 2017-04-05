package com.fintech.orion.exception;

/**
 * Created by TharinduMP on 4/3/2017.
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
