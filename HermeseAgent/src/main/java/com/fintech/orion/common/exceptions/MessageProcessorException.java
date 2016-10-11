package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class MessageProcessorException extends Exception {
    public MessageProcessorException(Exception e) {
        super(e);
    }

    public MessageProcessorException(String message) {
        super(message);
    }
}
