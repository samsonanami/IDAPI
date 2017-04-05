package com.fintech.orion.resource.upload.persistence;

import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.upload.UploadResource;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface ResourcePersistence {
    String persist(UploadResource uploadResource) throws PersistenceException;
}
