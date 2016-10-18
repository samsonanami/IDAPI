package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcessResource entity service class
 */
@Service
public class ProcessResourceService extends AbstractService<ProcessResource, Integer> implements ProcessResourceServiceInterface {

    @Autowired
    private ProcessResourceRepositoryInterface processResourceRepositoryInterface;

    @Transactional
    @Override
    public ProcessResource save(Process process, Resource resource, String resourceName) {
        ProcessResource processResource = new ProcessResource();
        processResource.setProcess(process);
        processResource.setResource(resource);
        processResource.setResourceName(resourceName);
        processResourceRepositoryInterface.saveOrUpdate(processResource);
        return processResource;
    }
}
