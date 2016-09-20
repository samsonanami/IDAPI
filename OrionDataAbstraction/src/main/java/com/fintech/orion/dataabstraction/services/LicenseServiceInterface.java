package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public interface LicenseServiceInterface {
    List<License> getLicenseList();

    License getLicenseById(int id)  throws ItemNotFoundException;

    void saveLicense(License license);

    void updateLicense(License license);

    boolean deleteLicenseById(int id) throws ItemNotFoundException;

    void deleteLicense(License license);
}
