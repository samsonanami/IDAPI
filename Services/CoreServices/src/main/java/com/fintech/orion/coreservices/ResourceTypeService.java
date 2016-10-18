package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import com.fintech.orion.dto.resourcetype.ResourceTypeDTO;
import com.fintech.orion.mapping.resourcetype.ResourceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ResourceType entity service class
 */
@Service
public class ResourceTypeService extends AbstractService<ResourceType, Integer> implements ResourceTypeServiceInterface {

    @Autowired
    private ResourceTypeRepositoryInterface resourceTypeRepositoryInterface;

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Transactional
    @Override
    public List<ResourceTypeDTO> getAllDTOs() {
        return resourceTypeMapper.resourceTypesToResourceTypeDTOs(getAll());
    }

    @Transactional
    @Override
    public ResourceTypeDTO findById(int id) throws ItemNotFoundException {
        return resourceTypeMapper.resourceTypeToResourceTypeDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ResourceTypeDTO resourceTypeDTO) {
        saveOrUpdate(resourceTypeMapper.resourceTypeDTOToResourceType(resourceTypeDTO));
    }

    @Transactional
    @Override
    public void delete(ResourceTypeDTO resourceTypeDTO) {
        delete(resourceTypeMapper.resourceTypeDTOToResourceType(resourceTypeDTO));
    }

    @Transactional
    @Override
    public ResourceType findByType(String type) throws ItemNotFoundException {
        return resourceTypeRepositoryInterface.findByType(type);
    }

}
