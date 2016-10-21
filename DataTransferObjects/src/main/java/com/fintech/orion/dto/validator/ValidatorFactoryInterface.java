package com.fintech.orion.dto.validator;

/**
 * Created by TharinduMP on 10/12/2016.
 */
@FunctionalInterface
public interface ValidatorFactoryInterface {
    ValidatorInterface getValidator(String dtoObjectName) throws ValidatorException;
}
