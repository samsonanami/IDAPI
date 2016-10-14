package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;

public interface ProcessingStatusRepositoryInterface extends DAOInterface<ProcessingStatus, Integer> {

    ProcessingStatus findByStatus(Status status) throws ItemNotFoundException;

}
