package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.exception.ResourceCreationException;

/**
 * Resource entity service interface
 */
public interface ResourceServiceInterface {

    void update(String resourceId, int processId, String resourceName) throws ItemNotFoundException;

    ResourceDTO createResourceForUser(String newFilename, String contentType, String clientName) throws ResourceCreationException;


}
