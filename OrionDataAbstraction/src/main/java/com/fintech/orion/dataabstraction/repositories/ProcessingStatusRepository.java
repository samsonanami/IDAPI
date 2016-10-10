package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;

public class ProcessingStatusRepository extends AbstractDAO<ProcessingStatus, Integer> implements ProcessingStatusRepositoryInterface {

    protected ProcessingStatusRepository() {
        super(ProcessingStatus.class);
    }

}
