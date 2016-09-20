package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ResourceService implements ResourceServiceInterface {

    @Autowired
    private ResourceRepositoryInterface repositoryInterface;

    @Override
    public List<Resource> getResourceList() {
        return repositoryInterface.getAll();
    }

    @Override
    public Resource getResourceById(int id) throws ItemNotFoundException {
        Resource resource = repositoryInterface.findById(id);
        if (resource != null) {
            return resource;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveResource(Resource resource) {
        repositoryInterface.saveOrUpdate(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        repositoryInterface.saveOrUpdate(resource);
    }

    @Override
    public boolean deleteResourceById(int id) throws ItemNotFoundException {
        Resource resource = repositoryInterface.findById(id);
        if(resource != null){
            repositoryInterface.delete(resource);
            return true;
        }
        return false;
    }

    @Override
    public void deleteResource(Resource resource) {
        repositoryInterface.delete(resource);
    }
}
