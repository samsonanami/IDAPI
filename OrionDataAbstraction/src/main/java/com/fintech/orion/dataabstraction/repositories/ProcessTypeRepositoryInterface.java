package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import org.springframework.data.repository.CrudRepository;

public interface ProcessTypeRepositoryInterface extends CrudRepository<ProcessType, Integer> {

    ProcessType findProcessTypeByType(String type);
}
