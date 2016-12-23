package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */
public class DirivingLicenseMRZValidatingException extends Exception{

    public DirivingLicenseMRZValidatingException(String message) {
        super(message);
    }

    public DirivingLicenseMRZValidatingException(String message, Exception exception) {
        super(message,exception);
    }
}
