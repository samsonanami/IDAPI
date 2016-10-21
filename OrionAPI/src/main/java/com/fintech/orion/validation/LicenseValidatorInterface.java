package com.fintech.orion.validation;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.exceptions.LicenseNotValidException;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;

import java.util.List;

/**
 * LicenseValidator interface
 */
public interface LicenseValidatorInterface {

    void checkLicenseForProcessTypes(List<ProcessTypeDTO> processTypeDTOs, int licenseId) throws ItemNotFoundException, LicenseNotValidException;

}
