package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ResourceTypeRepository extends AbstractConfigDAO<ResourceType, Integer> implements ResourceTypeRepositoryInterface {

    protected ResourceTypeRepository(Class<ResourceType> entityClass) {
        super(entityClass);
    }

}
