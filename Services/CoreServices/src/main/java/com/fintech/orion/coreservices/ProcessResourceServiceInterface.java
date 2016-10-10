package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessResourceServiceInterface {
    List<ProcessResource> getProcessResourceList();

    ProcessResource getProcessResourceById(int id) throws ItemNotFoundException;

    void saveOrUpdateProcessResource(ProcessResource processResource);

    boolean deleteProcessResourceById(int id) throws ItemNotFoundException;

    void deleteProcessResource(ProcessResource processResource);

    ProcessResource saveProcessResource(Process process, Resource resource, String resourceName);
}
