package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface ResourceTypeRepositoryInterface extends CrudRepository<ResourceType, Integer> {

    ResourceType findResourceTypeByType(String type);

}
