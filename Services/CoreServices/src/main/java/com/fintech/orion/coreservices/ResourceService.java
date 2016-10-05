package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceService implements ResourceServiceInterface {

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Transactional
    @Override
    public List<Resource> getResourceList() {
        return resourceRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public Resource getResourceById(int id) throws ItemNotFoundException {
        Resource resource = resourceRepositoryInterface.findById(id);
        if (resource != null) {
            return resource;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveResource(Resource resource) {
        resourceRepositoryInterface.saveOrUpdate(resource);
    }

    @Transactional
    @Override
    public void updateResource(Resource resource) {
        resourceRepositoryInterface.saveOrUpdate(resource);
    }

    @Transactional
    @Override
    public boolean deleteResourceById(int id) throws ItemNotFoundException {
        Resource resource = resourceRepositoryInterface.findById(id);
        if(resource != null){
            resourceRepositoryInterface.delete(resource);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteResource(Resource resource) {
        resourceRepositoryInterface.delete(resource);
    }
}
