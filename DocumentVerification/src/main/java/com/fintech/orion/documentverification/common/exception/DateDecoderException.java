package com.fintech.orion.documentverification.common.exception;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class DateDecoderException extends Exception {

    public DateDecoderException(String message){
        super(message);
    }

    public DateDecoderException(String message, Exception e){
        super(message, e);
    }
}
