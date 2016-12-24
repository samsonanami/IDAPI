package com.fintech.orion.common.exceptions;

/**
 * Created by sasitha on 12/20/16.
 *
 */
public class HermeseResponseprocessorException extends Exception{

    public HermeseResponseprocessorException(String message){
        super(message);
    }

    public HermeseResponseprocessorException(String message, Exception e){
        super(message, e);
    }

    public HermeseResponseprocessorException(Exception e){
        super(e);
    }
}
