package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

/**
 * ResourceType entity service interface
 */
public interface ResourceTypeServiceInterface extends ServiceInterface<ResourceType, Integer> {

    ResourceType findByType(String type) throws ItemNotFoundException;

}
