package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessTypeRepositoryInterface extends CrudRepository<ProcessType, Integer> {

    ProcessType findProcessTypeByType(String type);

    List<ProcessType> findProcessTypesByTypeIn(List<String> typeList);
}
