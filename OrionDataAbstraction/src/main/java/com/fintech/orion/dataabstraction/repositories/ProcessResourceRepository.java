package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ProcessResourceRepository extends AbstractConfigDAO<ProcessResource, Integer> implements ProcessResourceRepositoryInterface {

    protected ProcessResourceRepository(Class<ProcessResource> entityClass) {
        super(entityClass);
    }

}
