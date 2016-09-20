package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.Resource;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ResourceRepository extends AbstractConfigDAO<Resource, Integer> implements ResourceRepositoryInterface {

    protected ResourceRepository(Class<Resource> entityClass) {
        super(entityClass);
    }

}
