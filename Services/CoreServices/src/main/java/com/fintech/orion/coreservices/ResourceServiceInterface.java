package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.resource.ResourceDTO;

/**
 * Resource entity service interface
 */
public interface ResourceServiceInterface {

    void update(String resourceId, int processId, String resourceName) throws ItemNotFoundException;

    ResourceDTO save(String newFilename, String uuidNumber, String contentType, String accessToken) throws ItemNotFoundException;

    ResourceDTO findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException;

}
