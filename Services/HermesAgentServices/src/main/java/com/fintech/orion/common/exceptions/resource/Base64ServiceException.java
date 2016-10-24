package com.fintech.orion.common.exceptions.resource;

/**
 * Created by TharinduMP on 10/6/2016.
 * Base64ServiceException
 */
public class Base64ServiceException extends Exception {
    public Base64ServiceException(Exception e) {
        super(e);
    }

    public Base64ServiceException(String message) {
        super(message);
    }
}
