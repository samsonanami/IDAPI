package com.fintech.orion.exception;

/**
 * Created by sasitha on 12/22/16.
 */
public class FileHandlerException extends Exception {
    public FileHandlerException(String message){
        super(message);
    }
    public FileHandlerException(String message, Exception e){
        super(message, e);
    }
    public FileHandlerException(Exception e){
        super(e);
    }
}
