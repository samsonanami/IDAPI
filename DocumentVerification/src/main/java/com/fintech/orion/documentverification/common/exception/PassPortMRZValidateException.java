package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */
public class PassPortMRZValidateException extends MRZValidatingException {
    public PassPortMRZValidateException(String message) {
        super(message);
    }

    public PassPortMRZValidateException(String message, Exception exception) {
        super(message, exception);
    }
}
