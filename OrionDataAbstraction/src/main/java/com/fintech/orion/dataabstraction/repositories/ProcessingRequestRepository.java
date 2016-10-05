package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessingRequestRepository extends AbstractDAO<ProcessingRequest, Integer> implements ProcessingRequestRepositoryInterface {

    protected ProcessingRequestRepository() {
        super(ProcessingRequest.class);
    }

}
