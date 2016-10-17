package com.fintech.orion;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class RequestHandlerException extends Exception {
    public RequestHandlerException(Exception e) {
        super(e);
    }

    public RequestHandlerException(String message) {
        super(message);
    }
}
