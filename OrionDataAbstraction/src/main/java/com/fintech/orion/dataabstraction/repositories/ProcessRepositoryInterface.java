package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface ProcessRepositoryInterface extends CrudRepository<Process, Integer> {

    Process findProcessByProcessIdentificationCode(String identificationCode) throws ItemNotFoundException;

}
