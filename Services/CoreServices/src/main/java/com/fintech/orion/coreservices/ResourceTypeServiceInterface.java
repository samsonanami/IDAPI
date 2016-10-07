package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ResourceTypeServiceInterface {

    List<ResourceType> getResourceTypeList();

    ResourceType getResourceTypeById(int id) throws ItemNotFoundException;

    void saveResourceType(ResourceType resourceType);

    void updateResourceType(ResourceType resourceType);

    boolean deleteResourceTypeById(int id) throws ItemNotFoundException;

    void deleteResourceType(ResourceType resourceType);

    ResourceType getResourceTypeByType(String type) throws ItemNotFoundException;
}
