package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ResourceServiceInterface {
    List<Resource> getResourceList();

    Resource getResourceById(int id) throws ItemNotFoundException;

    void saveResource(Resource resource);

    void updateResource(Resource resource);

    boolean deleteResourceById(int id) throws ItemNotFoundException;

    void deleteResource(Resource resource);
}
