package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessConfigRepositoryInterface  extends CrudRepository<ProcessConfig, ProcessConfigId> {

    List<ProcessConfig> findProcessConfigsById(int processType) throws ItemNotFoundException;

    ProcessConfig findProcessConfigByConfigurationKeyAndProcessTypeAndClient(ConfigurationKey configurationKey,
                                                                             ProcessType processType, Client client);
}
