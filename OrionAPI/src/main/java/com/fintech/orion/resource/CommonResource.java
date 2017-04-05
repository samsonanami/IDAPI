package com.fintech.orion.resource;

import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.upload.UploadResource;
import com.fintech.orion.resource.upload.persistence.ResourcePersistence;
import com.fintech.orion.resource.validation.Validation;
import com.fintech.orion.resource.validation.factory.ValidationFactory;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public class CommonResource implements Resource {

    private UploadResource uploadResource;
    private ValidationFactory validationFactory;
    private ResourcePersistence resourcePersistence;

    public CommonResource(UploadResource uploadResource, ValidationFactory validationFactory, ResourcePersistence resourcePersistence) {
        this.uploadResource = uploadResource;
        this.validationFactory = validationFactory;
        this.resourcePersistence = resourcePersistence;
    }

    @Override
    public ValidatorStatus validate() {

        Validation validation = validationFactory.getValidation(uploadResource.getResourceContentType());

        if(validation == null) {
            return new ValidatorStatus(false, "Resource Content Type is not supported");
        }

        return validation.validate(uploadResource);
    }

    @Override
    public String persist() throws PersistenceException {
        return resourcePersistence.persist(uploadResource);
    }
}
