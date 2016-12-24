package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResourceNameRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.exception.ResourceCreationException;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.resource.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Resource entity service class
 */
@Service
public class ResourceService implements ResourceServiceInterface {

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ResourceTypeRepositoryInterface resourceTypeRepositoryInterface;


    @Transactional
    @Override
    public void update(String resourceId, int processId, String resourceName) throws ItemNotFoundException {
        //resourceRepositoryInterface.update(resourceId, processId, resourceName);
    }

    @Override
    public ResourceDTO createResourceForUser(String newFilename, String contentType, String clientName) throws ResourceCreationException {
        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        ResourceType resourceType = resourceTypeRepositoryInterface.findResourceTypeByType(contentType);
        if (resourceType == null){
            throw new ResourceCreationException("Could not find resource type with name : " + contentType);
        }
        Resource resource = new Resource();
        resource.setClient(client);
        resource.setResourceType(resourceType);
        resource.setResourceIdentificationCode(UUID.randomUUID().toString());
        resource.setLocation(newFilename);

        resourceRepositoryInterface.save(resource);

        return resourceMapper.resourceToResourceDTO(resource);
    }
}
