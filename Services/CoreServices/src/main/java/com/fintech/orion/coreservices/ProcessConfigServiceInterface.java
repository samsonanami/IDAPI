package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;

import java.util.List;

/**
 * ProcessConfig entity service interface
 */
public interface ProcessConfigServiceInterface extends ServiceInterface<ProcessConfig, ProcessConfigId> {

    List<ProcessConfigDTO> getAllDTOs();

    void saveOrUpdate(ProcessConfigDTO processConfigDTO) throws ItemNotFoundException;

    void delete(ProcessConfigDTO processConfigDTO) throws ItemNotFoundException;

    List<ProcessConfigDTO> findById(int processType) throws ItemNotFoundException;

}
