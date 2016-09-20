package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.Process;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public class ProcessRepository extends AbstractConfigDAO<Process, Integer> implements ProcessRepositoryInterface {

    protected ProcessRepository(Class<Process> entityClass) {
        super(entityClass);
    }
}
