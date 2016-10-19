package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ProcessRepositoryInterface extends DAOInterface<Process, Integer> {

    Process findByIdentificationCode(String identificationCode) throws ItemNotFoundException;

}
