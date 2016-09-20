package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public interface ProcessResourceServiceInterface {
    List<ProcessResource> getProcessResourceList();

    ProcessResource getProcessResourceById(int id) throws ItemNotFoundException;

    void saveProcessResource(ProcessResource processResource);

    boolean deleteProcessResourceById(int id) throws ItemNotFoundException;

    void updateProcessResource(ProcessResource processResource);

    void deleteProcessResource(ProcessResource processResource);
}
