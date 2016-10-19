package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.resource.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Resource entity service class
 */
@Service
public class ResourceService extends AbstractService<Resource, Integer> implements ResourceServiceInterface {

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    private ResourceTypeServiceInterface resourceTypeServiceInterface;

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional
    @Override
    public List<ResourceDTO> getAllDTOs() {
        return resourceMapper.resourcesToResourceDTOs(getAll());
    }

    @Transactional
    @Override
    public ResourceDTO findById(int id) throws ItemNotFoundException {
        return resourceMapper.resourceToResourceDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ResourceDTO resourceDTO) {
        saveOrUpdate(resourceMapper.resourceDTOToResource(resourceDTO));
    }

    @Transactional
    @Override
    public void saveOrUpdate(String resourceId, int processId) throws ItemNotFoundException {
        resourceRepositoryInterface.saveOrUpdate(resourceId, processId);
    }

    @Transactional
    @Override
    public void delete(ResourceDTO resourceDTO) {
        delete(resourceMapper.resourceDTOToResource(resourceDTO));
    }

    @Transactional
    @Override
    public ResourceDTO save(String newFilename, String uuidNumber, String contentType, String accessToken) throws ItemNotFoundException {
        ResourceType resourceType = resourceTypeServiceInterface.findByType(contentType);
        Client client = clientMapper.clientDTOToClient(clientServiceInterface.findByAuthToken(accessToken));

        Resource resource = new Resource();
        resource.setLocation(newFilename);
        resource.setResourceType(resourceType);
        resource.setClient(client);
        resource.setResourceIdentificationCode(uuidNumber);

        resourceRepositoryInterface.saveOrUpdate(resource);
        return resourceMapper.resourceToResourceDTO(resource);
    }

    @Transactional
    @Override
    public ResourceDTO findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException {
        return resourceMapper.resourceToResourceDTO(resourceRepositoryInterface.findByIdentificationCode(resourceIdentificationCode));
    }
}