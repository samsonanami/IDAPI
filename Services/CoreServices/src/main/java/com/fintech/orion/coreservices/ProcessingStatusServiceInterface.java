package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;

import java.util.List;

/**
 * ProcessingStatus entity service interface
 */
public interface ProcessingStatusServiceInterface extends ServiceInterface<ProcessingStatus, Integer> {

    List<ProcessingStatusDTO> getAllDTOs();

    ProcessingStatusDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ProcessingStatusDTO processingStatusDTO);

    void delete(ProcessingStatusDTO processingStatusDTO);

    ProcessingStatusDTO findByStatus(Status status) throws ItemNotFoundException;
}
