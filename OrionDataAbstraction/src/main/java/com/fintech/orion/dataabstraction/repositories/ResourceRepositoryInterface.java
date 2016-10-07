package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ResourceRepositoryInterface extends DAOInterface<Resource, Integer> {
    Resource getResourceByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException;
}
