package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ResourceType entity service class
 */
@Service
public class ResourceTypeService implements ResourceTypeServiceInterface {

    @Autowired
    private ResourceTypeRepositoryInterface resourceTypeRepositoryInterface;

    @Transactional
    @Override
    public ResourceType findByType(String type) throws ItemNotFoundException {
        return resourceTypeRepositoryInterface.findResourceTypeByType(type);
    }

}
