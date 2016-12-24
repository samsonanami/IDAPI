package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sasitha on 12/18/16.
 */
@Repository
public interface ResourceNameRepositoryInterface extends CrudRepository<ResourceName, Integer>{

    ResourceName findResourceNameByName(String name);
}
