package com.fintech.orion.common.exceptions;

/**
 * Created by sasitha on 2/18/17.
 */
public class ConfigurationProvidorException extends Exception {

    public ConfigurationProvidorException(String message){
        super(message);
    }

    public ConfigurationProvidorException(String message, Exception e){
        super(message, e);
    }
}
