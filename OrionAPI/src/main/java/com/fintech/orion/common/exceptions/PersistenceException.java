package com.fintech.orion.common.exceptions;

/**
 * Created by TharinduMP on 10/28/2016.
 * PersistenceException
 */
public class PersistenceException extends Exception {

    public PersistenceException(Exception e) {
        super(e);
    }

    public PersistenceException(String message) {
        super(message);
    }
}
