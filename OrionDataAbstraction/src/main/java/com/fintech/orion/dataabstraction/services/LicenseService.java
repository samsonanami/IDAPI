package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public class LicenseService implements LicenseServiceInterface {

    @Autowired
    private LicenseRepositoryInterface repositoryInterface;

    @Override
    public List<License> getLicenseList() {
        return repositoryInterface.getAll();
    }

    @Override
    public License getLicenseById(int id) throws ItemNotFoundException {
        License license = repositoryInterface.findById(id);
        if (license != null) {
            return license;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveLicense(License license) {
        repositoryInterface.saveOrUpdate(license);
    }

    @Override
    public void updateLicense(License license) {
        repositoryInterface.saveOrUpdate(license);
    }

    @Override
    public boolean deleteLicenseById(int id) throws ItemNotFoundException {
        License license = repositoryInterface.findById(id);
        if(license != null){
            repositoryInterface.delete(license);
            return true;
        }
        return false;
    }

    @Override
    public void deleteLicense(License license) {
        repositoryInterface.delete(license);
    }
}
