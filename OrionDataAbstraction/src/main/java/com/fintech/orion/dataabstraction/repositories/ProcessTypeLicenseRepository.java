package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessTypeLicenseRepository extends AbstractDAO<ProcessTypeLicense , Integer> implements ProcessTypeLicenseRepositoryInterface {

    protected ProcessTypeLicenseRepository() {
        super(ProcessTypeLicense.class);
    }

}
