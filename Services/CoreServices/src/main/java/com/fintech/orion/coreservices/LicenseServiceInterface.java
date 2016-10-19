package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.license.LicenseDTO;

import java.util.List;

/**
 * License entity service interface
 */
public interface LicenseServiceInterface extends ServiceInterface<License, Integer> {

    List<LicenseDTO> getAllDTOs();

    LicenseDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(LicenseDTO clientLicenseDTO);

    void delete(LicenseDTO clientLicenseDTO);

}
