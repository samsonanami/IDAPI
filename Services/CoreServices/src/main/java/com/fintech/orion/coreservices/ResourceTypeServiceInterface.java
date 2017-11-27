package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

/**
 * ResourceType entity service interface
 */
public interface ResourceTypeServiceInterface {

    ResourceType findByType(String type) throws ItemNotFoundException;
    ResourceType findById(int id) throws ItemNotFoundException;
}
