package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ProcessTypeRepositoryInterface extends DAOInterface<ProcessType, Integer> {

    ProcessType getProcessTypeByName(String type) throws ItemNotFoundException;

}
