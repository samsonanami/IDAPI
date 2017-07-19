package com.fintech.orion.common.exceptions.job;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class JobHandlerException extends Exception {
    public JobHandlerException(Exception e) {
        super(e);
    }

    public JobHandlerException(String message) {
        super(message);
    }
}
