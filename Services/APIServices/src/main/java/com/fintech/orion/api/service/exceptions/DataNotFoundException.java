package com.fintech.orion.api.service.exceptions;

/**
 * Created by sasitha on 12/27/16.
 */
public class DataNotFoundException extends Exception {

    public DataNotFoundException(String s){
        super(s);
    }

    public DataNotFoundException(String s, Exception e){
        super(s, e);
    }
}
