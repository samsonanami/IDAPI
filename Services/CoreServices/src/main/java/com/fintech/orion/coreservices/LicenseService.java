package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import com.fintech.orion.dto.license.LicenseDTO;
import com.fintech.orion.mapping.license.LicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * License entity service class
 */
@Service
public class LicenseService extends AbstractService<License, Integer> implements LicenseServiceInterface {

    @Autowired
    private LicenseMapper licenseMapper;

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Transactional
    @Override
    public List<LicenseDTO> getAllDTOs() {
        return licenseMapper.licensesToLicenseDTOs(licenseRepositoryInterface.getAll());
    }

    @Transactional
    @Override
    public LicenseDTO findById(int id) throws ItemNotFoundException {
        return licenseMapper.licenseToLicenseDTO(licenseRepositoryInterface.findById(id));
    }

    @Transactional
    @Override
    public void saveOrUpdate(LicenseDTO licenseDTO) {
        licenseRepositoryInterface.saveOrUpdate(licenseMapper.licenseDTOToLicense(licenseDTO));
    }

    @Transactional
    @Override
    public void delete(LicenseDTO licenseDTO) {
        licenseRepositoryInterface.delete(licenseMapper.licenseDTOToLicense(licenseDTO));
    }
}
