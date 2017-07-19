package com.fintech.orion.common.exceptions.job;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class JobProducerException extends Exception {
    public JobProducerException(Exception e) {
        super(e);
    }

    public JobProducerException(String message) {
        super(message);
    }
}
