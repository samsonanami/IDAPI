package com.fintech.orion.exception;

/**
 * Created by sasitha on 12/18/16.
 *
 */
public class ResourceCreationException extends Exception{

    public ResourceCreationException(String message){
        super(message);
    }

    public ResourceCreationException(String message, Throwable e){
        super(message, e);
    }

    public ResourceCreationException(Throwable e){
        super(e);
    }
}
