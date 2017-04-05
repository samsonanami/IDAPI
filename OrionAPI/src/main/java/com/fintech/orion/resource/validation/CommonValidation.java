package com.fintech.orion.resource.validation;

import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.resource.support.ResourceSupport;
import com.fintech.orion.resource.upload.UploadResource;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public class CommonValidation extends AbstractValidation implements Validation {

    private ResourceSupport resourceSupport;

    public CommonValidation(ResourceSupport resourceSupport) {
        this.resourceSupport = resourceSupport;
    }

    @Override
    public ValidatorStatus validate(UploadResource uploadResource) {
        ValidatorStatus validatorStatus = super.validate(uploadResource);
        if (validatorStatus != null && !validatorStatus.isPassed()) {
            return validatorStatus;
        }

        if(!resourceSupport.isSupportExtension(uploadResource.getResourceExtension())) {
            return new ValidatorStatus(false, "Resource Extension is not Supported.");
        }


        if(!resourceSupport.isSupportFileSize(uploadResource.getResourceExtension(), uploadResource.getResourceSize())) {
            return new ValidatorStatus(false, "Resource Size is beyond the allowed limit");
        }

        return new ValidatorStatus(true);
    }
}
