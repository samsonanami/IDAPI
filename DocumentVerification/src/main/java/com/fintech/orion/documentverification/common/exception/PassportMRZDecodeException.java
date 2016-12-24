package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */

public class PassportMRZDecodeException extends Exception{

    public PassportMRZDecodeException(String message) {
        super(message);
    }

    public PassportMRZDecodeException(String message, Exception exception) {
        super(message,exception);
    }
}
