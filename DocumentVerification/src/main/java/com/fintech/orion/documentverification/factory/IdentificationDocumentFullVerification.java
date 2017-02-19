package com.fintech.orion.documentverification.factory;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/26/16.
 */
public class IdentificationDocumentFullVerification extends AbstractCustomValidation implements DocumentVerification {
    private static final String PASSPORT = "passport";
    private static final String DRIVING_LICENSE_FRONT = "drivingLicenseFront";

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    @Qualifier("idDocCustomValidations")
    private List idDocCustomValidations;

    @Override
    @Transactional
    public List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse, Map<String, VerificationConfiguration> configurations) {

        ProcessingRequest processingRequest = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(ocrResponse.getVerificationRequestId());


        Process documentVerificationProcess = processRepositoryInterface
                .findProcessByProcessingRequestAndProcessType(processingRequest.getProcessingRequestIdentificationCode(),
                        "idVerification");

        Resource idVerificationResource = null;
        for (Resource resource : documentVerificationProcess.getResources()) {
            if (idVerificationResource == null && (PASSPORT.equalsIgnoreCase(resource.getResourceName().getName())
                    || DRIVING_LICENSE_FRONT.equalsIgnoreCase(resource.getResourceName().getName()))) {
                idVerificationResource = resource;
            }
        }
        ResourceName resourceName = new ResourceName();
        if (idVerificationResource != null) {
            resourceName = idVerificationResource.getResourceName();
        }

        return executeCustomValidations(getCustomValidationList(), resourceName, ocrResponse);
    }

    private List<CustomValidation> getCustomValidationList() {
        return idDocCustomValidations;
    }
}
