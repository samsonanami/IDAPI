package com.fintech.orion.common.exceptions.license;

/**
 * Created by sasitha on 1/15/17.
 *
 */
public class LicenseHandlerException extends Exception{

    public LicenseHandlerException(String message, Exception e){
        super(message, e);
    }
}
