package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
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

    @Transactional
    @Override
    public List<LicenseDTO> getAllDTOs() {
        return licenseMapper.licensesToLicenseDTOs(getAll());
    }

    @Transactional
    @Override
    public LicenseDTO findById(int id) throws ItemNotFoundException {
        return licenseMapper.licenseToLicenseDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(LicenseDTO licenseDTO) {
        saveOrUpdate(licenseMapper.licenseDTOToLicense(licenseDTO));
    }

    @Transactional
    @Override
    public void delete(LicenseDTO licenseDTO) {
        delete(licenseMapper.licenseDTOToLicense(licenseDTO));
    }
}
