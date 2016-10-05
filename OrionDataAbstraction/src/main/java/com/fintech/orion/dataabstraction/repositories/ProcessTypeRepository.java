package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessTypeRepository extends AbstractDAO<ProcessType, Integer> implements ProcessTypeRepositoryInterface {

    protected ProcessTypeRepository() {
        super(ProcessType.class);
    }
}
