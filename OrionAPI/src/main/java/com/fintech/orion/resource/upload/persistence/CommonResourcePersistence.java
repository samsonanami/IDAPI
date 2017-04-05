package com.fintech.orion.resource.upload.persistence;

import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.persistence.workflow.PersistenceWorkflow;
import com.fintech.orion.resource.persistence.workflow.factory.PersistenceWorkflowFactory;
import com.fintech.orion.resource.upload.UploadResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 3/29/2017.
 */
@Component
public class CommonResourcePersistence implements ResourcePersistence {

    @Autowired
    private PersistenceWorkflowFactory persistenceWorkflowFactory;

    @Override
    public String persist(UploadResource uploadResource) throws PersistenceException {
        PersistenceWorkflow persistenceWorkflow = persistenceWorkflowFactory.getPersistenceWorkflow(uploadResource.getResourceExtension());
        return persistenceWorkflow.execute(uploadResource);
    }
}
