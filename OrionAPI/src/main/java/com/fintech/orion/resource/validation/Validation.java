package com.fintech.orion.resource.validation;

import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.resource.upload.UploadResource;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface Validation {
    ValidatorStatus validate(UploadResource uploadResource);
}
