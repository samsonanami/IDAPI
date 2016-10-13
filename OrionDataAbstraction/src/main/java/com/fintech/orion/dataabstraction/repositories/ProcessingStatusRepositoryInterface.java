package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;

public interface ProcessingStatusRepositoryInterface extends DAOInterface<ProcessingStatus, Integer> {

    ProcessingStatus findByStatus(String status);

}
