package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;

import java.util.List;

/**
 * ProcessTypeLicense entity service interface
 */
public interface ProcessTypeLicenseServiceInterface {

    List<ProcessTypeLicenseDTO> getAllForLicenseId(int licenseId) throws ItemNotFoundException;

}
