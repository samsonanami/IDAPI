package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;

/**
 * ProcessingStatus entity service interface
 */
public interface ProcessingStatusServiceInterface extends ServiceInterface<ProcessingStatus, Integer> {

    ProcessingStatus findByStatus(Status status) throws ItemNotFoundException;
}
