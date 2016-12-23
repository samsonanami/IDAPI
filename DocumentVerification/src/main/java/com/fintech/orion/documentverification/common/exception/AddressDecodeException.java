package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/19/2016.
 */
public class AddressDecodeException extends Exception{
    public AddressDecodeException(String message) {
        super(message);
    }

    public AddressDecodeException(String message,Exception exception) {
        super(message,exception);
    }
}
