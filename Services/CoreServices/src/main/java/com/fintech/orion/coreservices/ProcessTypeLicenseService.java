package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeLicenseRepositoryInterface;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;
import com.fintech.orion.mapping.processtypelicense.ProcessTypeLicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProcessTypeLicense entity service class
 */
@Service
public class ProcessTypeLicenseService implements ProcessTypeLicenseServiceInterface {

    @Autowired
    private ProcessTypeLicenseMapper processTypeLicenseMapper;

    @Autowired
    private ProcessTypeLicenseRepositoryInterface processTypeLicenseRepositoryInterface;

    @Override
    public List<ProcessTypeLicenseDTO> getAllForLicenseId(int licenseId) throws ItemNotFoundException {
        return processTypeLicenseMapper.processTypeLicensesToProcessTypeLicenseDTOs(processTypeLicenseRepositoryInterface.getAllForLicenseId(licenseId));
    }

}
