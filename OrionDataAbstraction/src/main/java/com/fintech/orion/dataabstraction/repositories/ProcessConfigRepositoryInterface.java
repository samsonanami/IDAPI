package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessConfigRepositoryInterface  extends DAOInterface<ProcessConfig, ProcessConfigId> {

    List<ProcessConfig> findById(int processType) throws ItemNotFoundException;

}
