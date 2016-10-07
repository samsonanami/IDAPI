package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class DestinationHandlerException extends Exception {
    public DestinationHandlerException(Exception e) {
        super(e);
    }

    public DestinationHandlerException(String message) {
        super(message);
    }
}
