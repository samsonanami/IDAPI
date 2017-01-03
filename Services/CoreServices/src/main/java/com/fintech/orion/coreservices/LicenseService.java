package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import com.fintech.orion.dto.license.LicenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * License entity service class
 */
public class LicenseService {

   /* @Autowired
    private LicenseMapper licenseMapper;

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Transactional
    @Override
    public LicenseDTO findById(int id) throws ItemNotFoundException {
        return licenseMapper.licenseToLicenseDTO(licenseRepositoryInterface.findOne(id));
    }

    @Transactional
    @Override
    public void updateLicenseWithDTO(LicenseDTO licenseDTO) throws ItemNotFoundException {
        License license = licenseRepositoryInterface.findOne(licenseDTO.getId());
        licenseMapper.updateLicenseWithLicenseDTO(license,licenseDTO);
        licenseRepositoryInterface.save(license);
    }*/
}
