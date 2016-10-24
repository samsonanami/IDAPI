package com.fintech.orion.validation;

import com.fintech.orion.coreservices.ProcessTypeLicenseServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.exceptions.LicenseNotValidException;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * LicenseValidator class
 */
public class LicenseValidator implements LicenseValidatorInterface{

    @Autowired
    private ProcessTypeLicenseServiceInterface processTypeLicenseServiceInterface;

    @Override
    public void checkLicenseForProcessTypes(List<ProcessTypeDTO> processTypeDTOs, int licenseId) throws LicenseNotValidException {
        List<ProcessTypeLicenseDTO> processTypeLicenseDTOs = null;
        try {
            processTypeLicenseDTOs = processTypeLicenseServiceInterface.getAllForLicenseId(licenseId);
        } catch (ItemNotFoundException e) {
            throw new LicenseNotValidException("License not found:" + e);
        }

        List<ProcessTypeDTO> processTypeDTOList = new ArrayList<>();
        for(ProcessTypeLicenseDTO p : processTypeLicenseDTOs){
            processTypeDTOList.add(p.getProcessTypeDTO());
        }
        if(!processTypeDTOList.containsAll(processTypeDTOs)){
            throw new LicenseNotValidException("License not valid");
        }
    }
}
