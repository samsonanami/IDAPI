package com.fintech.orion.hermesagentservices.license;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.license.LicenseHandlerException;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by sasitha on 1/15/17.
 *
 */
public class LicenseHandler implements LicenseHandlerInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseHandler.class);

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Override
    @Transactional
    public void updateLicense(String clientLicenseId, String rawString) throws LicenseHandlerException{
        LOGGER.debug("Updating license with license key {}", clientLicenseId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OcrResponse response = objectMapper.readValue(rawString, OcrResponse.class);
            if ("processing_successful".equalsIgnoreCase(response.getStatus())){
                License license = licenseRepositoryInterface.findLicenseByLicenseKey(clientLicenseId);
                int currentLicenseCount = license.getCurrentRequestCount();
                currentLicenseCount = currentLicenseCount + 1;
                license.setCurrentRequestCount(currentLicenseCount);
                licenseRepositoryInterface.save(license);
            }
        } catch (IOException e) {
            throw new LicenseHandlerException("Error occurred while updating the license key : "+ clientLicenseId, e);
        } catch (ItemNotFoundException e) {
            throw new LicenseHandlerException("No license found with license key : "+ clientLicenseId, e);
        }
    }
}
