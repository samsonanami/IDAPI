package com.fintech.orion.resource.persistence.workflow;

import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.upload.UploadResource;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface PersistenceWorkflow {
    String execute(UploadResource uploadResource) throws PersistenceException;
}
