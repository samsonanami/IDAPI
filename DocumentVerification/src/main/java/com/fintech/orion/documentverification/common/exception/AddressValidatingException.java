package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */
public class AddressValidatingException extends Exception {
    public AddressValidatingException(String message) {
        super(message);
    }

    public AddressValidatingException(String message, Exception exception) {
        super(message,exception);
    }
}
