package com.fintech.orion.common.exceptions.payload;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class PayloadProcessingException extends Exception {
    public PayloadProcessingException(Exception e) {
        super(e);
    }

    public PayloadProcessingException(String message) {
        super(message);
    }

}
