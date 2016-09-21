package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceRepository extends AbstractDAO<Resource, Integer> implements ResourceRepositoryInterface {

    protected ResourceRepository() {
        super(Resource.class);
    }

}
