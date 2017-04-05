package com.fintech.orion.resource.persistence.exception;

import com.fintech.orion.exception.BusinessException;

/**
 * Created by TharinduMP on 3/30/2017.
 */
public class PersistenceBusinessException extends BusinessException {
    public PersistenceBusinessException(String message) {
        super(message);
    }
}
