package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessConfigRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import com.fintech.orion.mapping.processconfig.ProcessConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProcessConfig entity service class
 */
@Service
public class ProcessConfigService extends AbstractService<ProcessConfig, ProcessConfigId> implements ProcessConfigServiceInterface {

    @Autowired
    private ProcessConfigRepositoryInterface processConfigRepositoryInterface;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Autowired
    private ProcessConfigMapper processConfigMapper;

    @Transactional
    @Override
    public List<ProcessConfigDTO> getAllDTOs() {
        return processConfigMapper.processConfigsToProcessConfigDTOs(getAll());
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessConfigDTO processConfigDTO) throws ItemNotFoundException {
        ProcessType processType = processTypeRepositoryInterface.findById(processConfigDTO.getProcessType());

        ProcessConfigId processConfigId = new ProcessConfigId();
        processConfigId.setKey(processConfigDTO.getKey());
        processConfigId.setProcessType(processConfigDTO.getProcessType());

        ProcessConfig processConfig = new ProcessConfig();
        processConfig.setId(processConfigId);
        processConfig.setProcessType(processType);
        processConfig.setValue(processConfigDTO.getValue());

        saveOrUpdate(processConfig);
    }

    @Transactional
    @Override
    public void delete(ProcessConfigDTO processConfigDTO) throws ItemNotFoundException {
        ProcessType processType = processTypeRepositoryInterface.findById(processConfigDTO.getProcessType());

        ProcessConfigId processConfigId = new ProcessConfigId();
        processConfigId.setKey(processConfigDTO.getKey());
        processConfigId.setProcessType(processConfigDTO.getProcessType());

        ProcessConfig processConfig = new ProcessConfig();
        processConfig.setId(processConfigId);
        processConfig.setProcessType(processType);
        processConfig.setValue(processConfigDTO.getValue());

        delete(processConfig);
    }

    @Transactional
    @Override
    public List<ProcessConfigDTO> findById(int processType) throws ItemNotFoundException {
        return processConfigMapper.processConfigsToProcessConfigDTOs(processConfigRepositoryInterface.findById(processType));
    }
}
