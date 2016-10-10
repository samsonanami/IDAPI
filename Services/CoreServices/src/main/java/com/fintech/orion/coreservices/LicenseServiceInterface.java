package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface LicenseServiceInterface {
    List<License> getLicenseList();

    License getLicenseById(int id)  throws ItemNotFoundException;

    void saveOrUpdateLicense(License license);

    boolean deleteLicenseById(int id) throws ItemNotFoundException;

    void deleteLicense(License license);
}
