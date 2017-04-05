package com.fintech.orion.resource.builder;

import com.fintech.orion.resource.CommonResource;
import com.fintech.orion.resource.upload.UploadResource;
import com.fintech.orion.resource.upload.persistence.ResourcePersistence;
import com.fintech.orion.resource.validation.factory.ValidationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 4/3/2017.
 */
@Component
public class CommonResourceBuilder implements ResourceBuilder {

    @Autowired
    private ValidationFactory validationFactory;

    @Autowired
    private ResourcePersistence resourcePersistence;

    @Override
    public CommonResource build(UploadResource uploadResource) {
        return new CommonResource(uploadResource, validationFactory, resourcePersistence);
    }
}
