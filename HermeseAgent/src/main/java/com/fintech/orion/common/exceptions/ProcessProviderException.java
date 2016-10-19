package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class ProcessProviderException extends Exception {
    public ProcessProviderException(Exception e) {
        super(e);
    }

    public ProcessProviderException(String message) {
        super(message);
    }
}
