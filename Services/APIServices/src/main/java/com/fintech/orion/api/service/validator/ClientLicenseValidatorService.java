package com.fintech.orion.api.service.validator;

import com.fintech.orion.api.service.exceptions.ClientLicenseValidatorException;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.request.api.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by sasitha on 11/2/16.
 */
@Service
public class ClientLicenseValidatorService implements ClientLicenseValidatorServiceInterface {

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Override
    @Transactional
    public boolean validate(String licenseKey, VerificationRequest processingRequest) throws ClientLicenseValidatorException {
        boolean status;
        License license;
        try {
            license = licenseRepositoryInterface.findLicenseByLicenseKey(licenseKey);
        } catch (ItemNotFoundException e) {
            throw new ClientLicenseValidatorException("No license found for the authentication token :"+ licenseKey, e);
        }
        status = isAllProcessingRequestsAllowedByLicense(license, processingRequest);
        return status;
    }

    @Transactional
    private boolean isAllProcessingRequestsAllowedByLicense(License license, VerificationRequest processingRequest){
        boolean isAllowed = true;
        List<ProcessTypeLicense> processTypeLicenseList = new ArrayList<>(license.getProcessTypeLicenses());
        for (VerificationProcess process : processingRequest.getVerificationProcesses()){
            if(getProcessingTypeOccurrencesInAllowedTypesInLicense(process.getVerificationProcessType(), processTypeLicenseList) <= 0){
                isAllowed = false;
                break;
            }
        }
        return isAllowed;
    }

    @Transactional
    private int getProcessingTypeOccurrencesInAllowedTypesInLicense(String processType, List<ProcessTypeLicense> processingTypesInLicense){
        int count = 0;
        for (ProcessTypeLicense pl : processingTypesInLicense){
            if(pl.getProcessType().getType().equals(processType)){
                count ++;
            }
        }
        return count;
    }

}
