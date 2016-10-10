package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ProcessingRequestRepositoryInterface extends DAOInterface<ProcessingRequest, Integer> {
    ProcessingRequest findByIdIdentificationCode(String identificationCode) throws ItemNotFoundException;
}
