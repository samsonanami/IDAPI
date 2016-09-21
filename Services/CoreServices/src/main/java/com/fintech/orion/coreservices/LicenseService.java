package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LicenseService implements LicenseServiceInterface {

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Transactional
    @Override
    public List<License> getLicenseList() {
        return licenseRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public License getLicenseById(int id) throws ItemNotFoundException {
        License license = licenseRepositoryInterface.findById(id);
        if (license != null) {
            return license;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveLicense(License license) {
        licenseRepositoryInterface.saveOrUpdate(license);
    }

    @Transactional
    @Override
    public void updateLicense(License license) {
        licenseRepositoryInterface.saveOrUpdate(license);
    }

    @Transactional
    @Override
    public boolean deleteLicenseById(int id) throws ItemNotFoundException {
        License license = licenseRepositoryInterface.findById(id);
        if(license != null){
            licenseRepositoryInterface.delete(license);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteLicense(License license) {
        licenseRepositoryInterface.delete(license);
    }
}
