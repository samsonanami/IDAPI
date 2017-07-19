package com.fintech.orion.common.exceptions.request;

/**
 * Created by sasitha on 12/20/16.
 *
 */
public class RequestProcessorException extends Exception{

    public RequestProcessorException(String message){
        super(message);
    }

    public RequestProcessorException(String message, Exception e){
        super(message, e);
    }

    public RequestProcessorException(Exception e){
        super(e);
    }
}
