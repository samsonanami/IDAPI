package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.License;
import org.springframework.stereotype.Repository;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
@Repository
public class LicenseRepository extends AbstractConfigDAO<License, Integer> implements LicenseRepositoryInterface {

    protected LicenseRepository(Class<License> entityClass) {
        super(entityClass);
    }

}
