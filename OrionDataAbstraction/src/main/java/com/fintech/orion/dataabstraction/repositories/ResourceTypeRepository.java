package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceTypeRepository extends AbstractDAO<ResourceType, Integer> implements ResourceTypeRepositoryInterface {

    protected ResourceTypeRepository() {
        super(ResourceType.class);
    }

}
