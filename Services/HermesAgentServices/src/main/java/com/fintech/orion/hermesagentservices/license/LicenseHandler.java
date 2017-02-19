package com.fintech.orion.hermesagentservices.license;

import com.fintech.orion.common.exceptions.license.LicenseHandlerException;
import com.fintech.orion.common.service.VerificationRequestDetailService;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sasitha on 1/15/17.
 *
 */
public class LicenseHandler implements LicenseHandlerInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseHandler.class);

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Autowired
    private VerificationRequestDetailService verificationRequestDetailService;

    @Override
    @Transactional
    public void updateLicense(String clientLicenseId, String processingRequestId) throws LicenseHandlerException{

        List<Process> processList = verificationRequestDetailService.getProcessListBelongsToProcessingRequest(processingRequestId);

        LOGGER.debug("Updating license with license key {} with count {} ", clientLicenseId, processList.size());
        try {
            License license = licenseRepositoryInterface.findLicenseByLicenseKey(clientLicenseId);
            int currentLicenseCount = license.getCurrentRequestCount();
            currentLicenseCount = currentLicenseCount + processList.size();
            license.setCurrentRequestCount(currentLicenseCount);
            licenseRepositoryInterface.save(license);
        }catch (ItemNotFoundException e) {
            throw new LicenseHandlerException("No license found with license key : "+ clientLicenseId, e);
        }
    }
}
