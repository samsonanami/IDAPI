package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */
public class DrivingLicenseMRZDecodingException extends MRZDecodingException {

    public DrivingLicenseMRZDecodingException(String message) {
        super(message);
    }

    public DrivingLicenseMRZDecodingException(String message, Exception exception) {
        super(message, exception);
    }
}
