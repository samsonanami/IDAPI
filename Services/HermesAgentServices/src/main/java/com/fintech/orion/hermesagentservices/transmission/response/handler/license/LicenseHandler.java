package com.fintech.orion.hermesagentservices.transmission.response.handler.license;

import com.fintech.orion.coreservices.LicenseServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.license.LicenseDTO;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 10/24/2016.
 * LicenseHandler
 */
public class LicenseHandler implements LicenseHandlerInterface {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LicenseHandler.class);

    @Autowired
    private LicenseServiceInterface licenseService;

    @Override
    public void updateLicense(ProcessDTO processDTO, GenericRequest genericRequest) {

        //for the moment, update license on all instances
        try {
            //get license
            LicenseDTO licenseDTO = licenseService.findById(genericRequest.getLicenseId());

            //get license count
            Integer currentLicCount = licenseDTO.getCurrentRequestCount();

            //update count
            if(currentLicCount != null) {
                licenseDTO.setCurrentRequestCount(licenseDTO.getCurrentRequestCount() + 1);
            } else {
                licenseDTO.setCurrentRequestCount(1);
            }

            //save
            licenseService.updateLicenseWithDTO(licenseDTO);

        } catch (ItemNotFoundException e) {
            LOGGER.error("Provided License Id was not found. License count is not updated.", e);
        } catch (Exception e) {
            LOGGER.error("Updating License Count Failed", e);
        }

    }
}