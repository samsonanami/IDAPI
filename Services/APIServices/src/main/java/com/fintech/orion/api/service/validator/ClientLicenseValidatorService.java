package com.fintech.orion.api.service.validator;

import com.fintech.orion.api.service.exceptions.ClientLicenseValidatorException;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean validate(String licenseKey, ProcessingRequest processingRequest) throws ClientLicenseValidatorException {
        boolean status;
        License license;
        try {
            license = licenseRepositoryInterface.getLicenseByLicenseKey(licenseKey);
        } catch (ItemNotFoundException e) {
            throw new ClientLicenseValidatorException("No license found for the authentication token :"+ licenseKey, e);
        }
        status = isAllProcessingRequestsAllowedByLicense(license, processingRequest);
        return status;
    }

    private boolean isAllProcessingRequestsAllowedByLicense(License license, ProcessingRequest processingRequest){
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
