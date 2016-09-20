package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ResourceTypeService implements ResourceTypeServiceInterface {

    @Autowired
    private ResourceTypeRepositoryInterface repositoryInterface;

    @Override
    public List<ResourceType> getResourceTypeList() {
        return repositoryInterface.getAll();
    }

    @Override
    public ResourceType getResourceTypeById(int id) throws ItemNotFoundException {
        ResourceType resourceType = repositoryInterface.findById(id);
        if (resourceType != null) {
            return resourceType;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveResourceType(ResourceType resourceType) {
        repositoryInterface.saveOrUpdate(resourceType);
    }

    @Override
    public void updateResourceType(ResourceType resourceType) {
        repositoryInterface.saveOrUpdate(resourceType);
    }

    @Override
    public boolean deleteResourceTypeById(int id) throws ItemNotFoundException {
        ResourceType resourceType = repositoryInterface.findById(id);
        if(resourceType != null){
            repositoryInterface.delete(resourceType);
            return true;
        }
        return false;
    }

    @Override
    public void deleteResourceType(ResourceType resourceType) {
        repositoryInterface.delete(resourceType);
    }
}
