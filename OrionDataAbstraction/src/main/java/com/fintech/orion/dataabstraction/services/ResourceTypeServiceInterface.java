package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public interface ResourceTypeServiceInterface {

    List<ResourceType> getResourceTypeList();

    ResourceType getResourceTypeById(int id) throws ItemNotFoundException;

    void saveResourceType(ResourceType resourceType);

    void updateResourceType(ResourceType resourceType);

    boolean deleteResourceTypeById(int id) throws ItemNotFoundException;

    void deleteResourceType(ResourceType resourceType);
}
