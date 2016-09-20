package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public class ProcessingRequestRepository extends AbstractConfigDAO<ProcessingRequest, Integer> implements ProcessingRequestRepositoryInterface {

    protected ProcessingRequestRepository(Class<ProcessingRequest> entityClass) {
        super(entityClass);
    }

}
