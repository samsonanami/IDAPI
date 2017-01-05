package com.fintech.orion.documentverification.factory;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/30/16.
 *
 */
public class AddressDocumentFullVerification implements DocumentVerification {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressDocumentFullVerification.class);
    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    @Qualifier("addressDocCustomValidations")
    private List addressDocCustomValidations;

    @Override
    public List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse, Map<String, VerificationConfiguration> configurations) {
        ProcessingRequest processingRequest = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(ocrResponse.getVerificationRequestId());

        Process documentVerificationProcess = new Process();
        for (Process process : processingRequest.getProcesses()){
            if (process.getProcessType().getType().equalsIgnoreCase("addressVerification")){
                documentVerificationProcess = process;
            }
        }

        Resource addressVerificationResourceName = null;
        for (Resource resource : documentVerificationProcess.getResources()){
            if(addressVerificationResourceName == null && (resource.getResourceName().getName().equalsIgnoreCase("passport")
                    || resource.getResourceName().getName().equalsIgnoreCase("drivingLicenseFront"))){
                addressVerificationResourceName = resource;
            }
        }
        ResourceName resourceName = new ResourceName();
        if (addressVerificationResourceName != null){
           resourceName = addressVerificationResourceName.getResourceName();
        }

        List<Object> idDocFullValidationList = new ArrayList<>();
        ValidationData errorDataSet = new ValidationData();
        errorDataSet.setRemarks("");
        errorDataSet.setId("critical_error_set");
        LOGGER.debug("Starting custom validation with resource name {} and ocr response {}", resourceName, ocrResponse);
        for (CustomValidation validation : getCustomValidationList()){
            try {
                ValidationData validationData = validation.validate(resourceName, ocrResponse);
                idDocFullValidationList.add(validationData);
                if (!validationData.getValidationStatus() && validation.isCriticalValidation()){
                    errorDataSet.setRemarks(errorDataSet.getRemarks() + validationData.getRemarks());
                }
            } catch (CustomValidationException e) {
                LOGGER.error("Unable to execute custom validation {}", validation);
            }
        }

        idDocFullValidationList.add(errorDataSet);

        return idDocFullValidationList;
    }

    private List<CustomValidation> getCustomValidationList(){
        return addressDocCustomValidations;
    }
}