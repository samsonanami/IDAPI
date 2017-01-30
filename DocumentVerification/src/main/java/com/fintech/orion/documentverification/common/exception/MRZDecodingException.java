package com.fintech.orion.documentverification.common.exception;

/**
 * Created by sasitha on 1/18/17.
 *
 */
public class MRZDecodingException extends Exception{

    public MRZDecodingException(String message){
        super(message);
    }

    public MRZDecodingException(String message, Exception e){
        super(message, e);
    }
}
