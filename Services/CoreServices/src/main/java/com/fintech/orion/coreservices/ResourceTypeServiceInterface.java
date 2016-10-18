package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.resourcetype.ResourceTypeDTO;

import java.util.List;

/**
 * ResourceType entity service interface
 */
public interface ResourceTypeServiceInterface extends ServiceInterface<ResourceType, Integer> {

    List<ResourceTypeDTO> getAllDTOs();

    ResourceTypeDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ResourceTypeDTO resourceTypeDTO);

    void delete(ResourceTypeDTO resourceTypeDTO);

    ResourceType findByType(String type) throws ItemNotFoundException;

}
