package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ResourceTypeRepositoryInterface extends DAOInterface<ResourceType, Integer> {

    ResourceType findByType(String type) throws ItemNotFoundException;

}
