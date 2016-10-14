package com.fintech.orion.dto.validator;

/**
 * Created by TharinduMP on 10/12/2016.
 */
public interface ValidatorFactoryInterface {
    ValidatorInterface getValidator(Object dtoObject) throws ValidatorException;
}
