package com.fintech.orion.resource.persistence.workflow;

import com.fintech.orion.resource.file.name.Filename;
import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.upload.UploadResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by TharinduMP on 3/29/2017.
 */
@Component
public class CommonPersistenceWorkflow extends AbstractPersistenceWorkflow implements PersistenceWorkflow {

    @Autowired
    private Filename uuidFilename;

    @Override
    public String execute(UploadResource uploadResource) throws PersistenceException {
        try {
            String filename = uuidFilename.getUniqueFileName(uploadResource.getResourceExtension());
            persist(uploadResource.getResourceBytes(), filename);
            return filename;
        } catch (IOException e) {
            throw new PersistenceException("Getting Resource Bytes Failed", e);
        }
    }
}
