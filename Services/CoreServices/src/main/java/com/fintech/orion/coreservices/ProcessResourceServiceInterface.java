package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessResourceServiceInterface {
    List<ProcessResource> getProcessResourceList();

    ProcessResource getProcessResourceById(int id) throws ItemNotFoundException;

    void saveProcessResource(ProcessResource processResource);

    boolean deleteProcessResourceById(int id) throws ItemNotFoundException;

    void updateProcessResource(ProcessResource processResource);

    void deleteProcessResource(ProcessResource processResource);
}
