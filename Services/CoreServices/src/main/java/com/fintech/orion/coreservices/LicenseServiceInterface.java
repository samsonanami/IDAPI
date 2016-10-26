package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.license.LicenseDTO;

/**
 * License entity service interface
 */
public interface LicenseServiceInterface {

    LicenseDTO findById(int id) throws ItemNotFoundException;

    void updateLicenseWithDTO(LicenseDTO licenseDTO) throws ItemNotFoundException;
}
