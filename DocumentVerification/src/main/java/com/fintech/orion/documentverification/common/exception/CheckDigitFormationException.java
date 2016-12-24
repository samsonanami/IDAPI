package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */
public class CheckDigitFormationException extends Exception{

    public CheckDigitFormationException(String message) {
        super(message);
    }

    public CheckDigitFormationException(String message, Exception exception) {
        super(message,exception);
    }
}
