package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepositoryInterface extends CrudRepository<Resource, Integer> {

    Resource findResourceByResourceIdentificationCode(String resourceIdentificationCode);
}
