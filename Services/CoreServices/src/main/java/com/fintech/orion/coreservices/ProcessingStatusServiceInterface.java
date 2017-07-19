package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;

/**
 * ProcessingStatus entity service interface
 */
public interface ProcessingStatusServiceInterface {

    ProcessingStatusDTO findByStatus(Status status) throws ItemNotFoundException;
}
