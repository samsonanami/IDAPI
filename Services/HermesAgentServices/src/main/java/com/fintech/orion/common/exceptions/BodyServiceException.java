package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class BodyServiceException extends Exception {
    public BodyServiceException(Exception e) {
        super(e);
    }

    public BodyServiceException(String message) {
        super(message);
    }
}
