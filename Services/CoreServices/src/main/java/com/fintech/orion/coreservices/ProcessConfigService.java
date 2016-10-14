package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessConfigRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessConfigService extends AbstractService<ProcessConfig, ProcessConfigId> implements ProcessConfigServiceInterface {

    @Autowired
    private ProcessConfigRepositoryInterface processConfigRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessConfig> findById(int processType) throws ItemNotFoundException {
        return processConfigRepositoryInterface.findById(processType);
    }
}
