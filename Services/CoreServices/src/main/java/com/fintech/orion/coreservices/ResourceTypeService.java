package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceTypeService implements ResourceTypeServiceInterface {

    @Autowired
    private ResourceTypeRepositoryInterface resourceTypeRepositoryInterface;

    @Transactional
    @Override
    public List<ResourceType> getResourceTypeList() {
        return resourceTypeRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ResourceType getResourceTypeById(int id) throws ItemNotFoundException {
        ResourceType resourceType = resourceTypeRepositoryInterface.findById(id);
        if (resourceType != null) {
            return resourceType;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveResourceType(ResourceType resourceType) {
        resourceTypeRepositoryInterface.saveOrUpdate(resourceType);
    }

    @Transactional
    @Override
    public void updateResourceType(ResourceType resourceType) {
        resourceTypeRepositoryInterface.saveOrUpdate(resourceType);
    }

    @Transactional
    @Override
    public boolean deleteResourceTypeById(int id) throws ItemNotFoundException {
        ResourceType resourceType = resourceTypeRepositoryInterface.findById(id);
        if(resourceType != null){
            resourceTypeRepositoryInterface.delete(resourceType);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteResourceType(ResourceType resourceType) {
        resourceTypeRepositoryInterface.delete(resourceType);
    }
}
