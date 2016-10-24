package com.fintech.orion.common.exceptions.request;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class RequestException extends Exception {
    public RequestException(Exception e) {
        super(e);
    }

    public RequestException(String message) {
        super(message);
    }
}
