package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResourceService extends AbstractService<Resource, Integer> implements ResourceServiceInterface {

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    private ResourceTypeServiceInterface resourceTypeServiceInterface;

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Transactional
    @Override
    public Resource save(String newFilename, String uuidNumber, String contentType, String accessToken) throws ItemNotFoundException {
        ResourceType resourceType = resourceTypeServiceInterface.findByType(contentType);
        Client client = clientServiceInterface.findByAuthToken(accessToken);

        Resource resource = new Resource();
        resource.setLocation(newFilename);
        resource.setResourceType(resourceType);
        resource.setClient(client);
        resource.setResourceIdentificationCode(uuidNumber);

        resourceRepositoryInterface.saveOrUpdate(resource);
        return resource;
    }

    @Transactional
    @Override
    public Resource findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException {
        return resourceRepositoryInterface.findByIdentificationCode(resourceIdentificationCode);
    }
}
