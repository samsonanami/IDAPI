package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessTypeLicenseRepositoryInterface extends CrudRepository<ProcessTypeLicense, Integer> {

    List<ProcessTypeLicense> findProcessTypeLicenseByLicense(License license) throws ItemNotFoundException;

}

