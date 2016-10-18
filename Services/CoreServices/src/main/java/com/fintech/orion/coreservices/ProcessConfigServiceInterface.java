package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessConfigServiceInterface extends ServiceInterface<ProcessConfig, ProcessConfigId> {

    List<ProcessConfig> findById(int processType) throws ItemNotFoundException;

}
