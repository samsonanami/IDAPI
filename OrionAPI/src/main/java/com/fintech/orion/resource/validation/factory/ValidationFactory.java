package com.fintech.orion.resource.validation.factory;

import com.fintech.orion.resource.validation.Validation;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface ValidationFactory {
    Validation getValidation(String fileExtension);
}
