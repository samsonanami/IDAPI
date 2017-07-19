package com.fintech.orion.documentverification.common.exception;

/**
 * Created by sasitha on 1/18/17.
 */
public class MRZValidatingException extends Exception {

    public MRZValidatingException(String message){
        super(message);
    }

    public MRZValidatingException(String message, Exception e){
        super(message, e);
    }
}
