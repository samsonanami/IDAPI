package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class ConnectionHandlerException extends Exception {
    public ConnectionHandlerException(Exception e) {
        super(e);
    }

    public ConnectionHandlerException(String message) {
        super(message);
    }
}
