package com.fintech.orion.resource.builder;

import com.fintech.orion.resource.CommonResource;
import com.fintech.orion.resource.upload.UploadResource;

/**
 * Created by TharinduMP on 4/3/2017.
 */
public interface ResourceBuilder {
    CommonResource build(UploadResource uploadResource);
}
