package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ProcessTypeRepository extends AbstractConfigDAO<ProcessType, Integer> implements ProcessTypeRepositoryInterface {

    protected ProcessTypeRepository(Class<ProcessType> entityClass) {
        super(entityClass);
    }
}
