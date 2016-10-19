package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class RequestWorkerException extends Exception {
    public RequestWorkerException(Exception e) {
        super(e);
    }

    public RequestWorkerException(String message) {
        super(message);
    }
}
