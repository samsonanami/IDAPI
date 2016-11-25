package com.fintech.orion.api.service.exceptions;

/**
 * Created by sasitha on 11/2/16.
 */
public class ClientLicenseValidatorException extends Exception {
    public ClientLicenseValidatorException(Exception e){
        super(e);
    }

    public ClientLicenseValidatorException(String message, Exception e){
        super(message, e);
    }
}
