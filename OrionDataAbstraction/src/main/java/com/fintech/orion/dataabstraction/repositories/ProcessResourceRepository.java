package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessResourceRepository extends AbstractDAO<ProcessResource, Integer> implements ProcessResourceRepositoryInterface {

    protected ProcessResourceRepository() {
        super(ProcessResource.class);
    }

}
