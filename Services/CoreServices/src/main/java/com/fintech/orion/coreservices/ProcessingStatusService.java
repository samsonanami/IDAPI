package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProcessingStatusService extends AbstractService<ProcessingStatus, Integer> implements ProcessingStatusServiceInterface {

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Transactional
    @Override
    public ProcessingStatus findByStatus(Status status) throws ItemNotFoundException {
        return processingStatusRepositoryInterface.findByStatus(status);
    }
}
