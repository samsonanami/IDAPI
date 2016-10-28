package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/28/2016.
 * PersistenceException
 */
public class DestinationProviderException extends Exception {

    public DestinationProviderException(Exception e) {
        super(e);
    }

    public DestinationProviderException(String message) {
        super(message);
    }
}
