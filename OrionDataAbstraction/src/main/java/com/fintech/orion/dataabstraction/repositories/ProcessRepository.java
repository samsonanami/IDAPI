package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessRepository extends AbstractDAO<Process, Integer> implements ProcessRepositoryInterface {

    protected ProcessRepository() {
        super(Process.class);
    }
}
