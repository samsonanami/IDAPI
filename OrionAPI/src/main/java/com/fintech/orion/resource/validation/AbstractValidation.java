package com.fintech.orion.resource.validation;

import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.resource.upload.UploadResource;

import java.util.Objects;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public abstract class AbstractValidation implements Validation {

    @Override
    public ValidatorStatus validate(UploadResource uploadResource) {

        if (uploadResource.isResourceNull()) {
            return new ValidatorStatus(false, "No Resource was provided.");
        }

        if(Objects.equals(uploadResource.getResourceExtension(), "")) {
            return new ValidatorStatus(false, "Resource Extension is not properly formatted.");
        }
        return null;
    }
}
