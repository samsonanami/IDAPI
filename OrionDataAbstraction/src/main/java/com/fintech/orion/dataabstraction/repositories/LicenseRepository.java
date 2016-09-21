package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.License;
import org.springframework.stereotype.Repository;

@Repository
public class LicenseRepository extends AbstractDAO<License, Integer> implements LicenseRepositoryInterface {

    protected LicenseRepository() {
        super(License.class);
    }

}
