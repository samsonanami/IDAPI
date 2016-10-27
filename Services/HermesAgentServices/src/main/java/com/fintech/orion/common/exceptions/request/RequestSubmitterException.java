package com.fintech.orion.common.exceptions.request;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class RequestSubmitterException extends Exception {
    public RequestSubmitterException(Exception e) {
        super(e);
    }

    public RequestSubmitterException(String message) {
        super(message);
    }
}
