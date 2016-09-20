package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ProcessTypeLicenseRepository extends AbstractConfigDAO<ProcessTypeLicense , Integer> implements ProcessTypeLicenseRepositoryInterface {

    protected ProcessTypeLicenseRepository(Class<ProcessTypeLicense> entityClass) {
        super(entityClass);
    }

}
