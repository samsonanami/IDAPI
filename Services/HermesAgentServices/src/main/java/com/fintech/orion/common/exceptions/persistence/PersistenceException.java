package com.fintech.orion.common.exceptions.persistence;

/**
 * Created by TharinduMP on 10/24/2016.
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
