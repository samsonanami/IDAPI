package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessConfigRepository extends AbstractDAO<ProcessConfig, Integer> implements ProcessConfigRepositoryInterface {

    protected ProcessConfigRepository() {
        super(ProcessConfig.class);
    }

}
