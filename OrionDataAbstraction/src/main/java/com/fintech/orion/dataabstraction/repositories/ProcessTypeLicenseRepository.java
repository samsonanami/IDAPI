package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessTypeLicenseRepository extends AbstractDAO<ProcessTypeLicense , Integer> implements ProcessTypeLicenseRepositoryInterface {

    protected ProcessTypeLicenseRepository() {
        super(ProcessTypeLicense.class);
    }

    public static final String LICENSE = "license";

    @Override
    public List<ProcessTypeLicense> getAllForLicenseId(int licenseId) throws ItemNotFoundException {
        List<ProcessTypeLicense> processTypeLicenses = findByCriteria(Restrictions.eq(LICENSE, licenseId));
        if (processTypeLicenses != null && !processTypeLicenses.isEmpty()) {
            return processTypeLicenses;
        } else {
            throw new ItemNotFoundException("ProcessTypeLicenses not found");
        }
    }
}
