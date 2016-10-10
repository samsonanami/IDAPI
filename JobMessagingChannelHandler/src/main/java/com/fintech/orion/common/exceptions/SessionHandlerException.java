package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class SessionHandlerException extends Exception {
    public SessionHandlerException(Exception e) {
        super(e);
    }

    public SessionHandlerException(String message) {
        super(message);
    }
}
