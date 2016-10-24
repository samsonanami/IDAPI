package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class FailedRequestException extends Exception {
    public FailedRequestException(Exception e) {
        super(e);
    }

    public FailedRequestException(String message, Exception e ) {
        super(message, e);
    }
}
