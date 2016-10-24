package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.license.LicenseDTO;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;

import java.util.List;

/**
 * ProcessTypeLicense entity service interface
 */
public interface ProcessTypeLicenseServiceInterface extends ServiceInterface<ProcessTypeLicense, Integer> {

    List<ProcessTypeLicenseDTO> getAllDTOs();

    ProcessTypeLicenseDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ProcessTypeLicenseDTO processTypeLicenseDTO);

    void delete(ProcessTypeLicenseDTO processTypeLicenseDTO);

    List<ProcessTypeLicenseDTO> getAllForLicenseId(int licenseId) throws ItemNotFoundException;

}
