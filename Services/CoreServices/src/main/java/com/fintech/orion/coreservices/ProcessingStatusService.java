package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.interceptor.ExcludeDefaultInterceptors;

public class ProcessingStatusService extends AbstractService<ProcessingStatus, Integer> implements ProcessingStatusServiceInterface {

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Override
    public ProcessingStatus findByStatus(String status) {
        return processingStatusRepositoryInterface.findByStatus(status);
    }
}
