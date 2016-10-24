package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessTypeLicenseRepositoryInterface extends DAOInterface<ProcessTypeLicense, Integer> {

    List<ProcessTypeLicense> getAllForLicenseId(int licenseId) throws ItemNotFoundException;

}

