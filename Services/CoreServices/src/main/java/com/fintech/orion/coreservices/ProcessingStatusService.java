package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcessingStatus entity service class
 */
public class ProcessingStatusService implements ProcessingStatusServiceInterface {

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Autowired
    private ProcessingStatusMapper processingStatusMapper;

    @Transactional
    @Override
    public ProcessingStatusDTO findByStatus(Status status) throws ItemNotFoundException {
        return processingStatusMapper.processingStatusToProcessingStatusDTO(processingStatusRepositoryInterface.findByStatus(status));
    }
}
