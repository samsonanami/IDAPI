package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.resource.ResourceDTO;

import java.util.List;

/**
 * Resource entity service interface
 */
public interface ResourceServiceInterface extends ServiceInterface<Resource, Integer> {

    List<ResourceDTO> getAllDTOs();

    ResourceDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ResourceDTO resourceDTO);

    void saveOrUpdate(String resourceId, int processId) throws ItemNotFoundException;

    void delete(ResourceDTO resourceDTO);

    ResourceDTO save(String newFilename, String uuidNumber, String contentType, String accessToken) throws ItemNotFoundException;

    ResourceDTO findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException;

}
