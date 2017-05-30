package com.fintech.orion.documentverification.factory;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/30/16.
 */
public class AddressDocumentFullVerification extends AbstractCustomValidation implements DocumentVerification {
    private static final String UTILITY_BILL = "utilityBill";
    private static final String DRIVING_LICENSE_FRONT = "drivingLicenseFront";
    private static final String ADDRESS_VERIFICATION = "addressVerification";
    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    @Qualifier("utilityBillCustomValidationsForAddressValidation")
    private List utilityBillCustomValidationsForAddressValidation;

    @Autowired
    @Qualifier("drivingLicenseCustomValidationsForAddressVerification")
    private List drivingLicenseCustomValidationsForAddressVerification;

    @Override
    @Transactional
    public List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse, Map<String, VerificationConfiguration> configurations) {
        ProcessingRequest processingRequest = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(ocrResponse.getVerificationRequestId());

        Process documentVerificationProcess = processRepositoryInterface
                .findProcessByProcessingRequestAndProcessType(processingRequest.getProcessingRequestIdentificationCode(),
                        ADDRESS_VERIFICATION);

        List<Object> verificationResults = new ArrayList<>();
        for (Resource resource : documentVerificationProcess.getResources()) {
            verificationResults.addAll(executeCustomValidations(
                    getCustomValidationList(resource.getResourceName().getName()), resource.getResourceName(), ocrResponse));
        }

        return verificationResults;
    }

    private List<CustomValidation> getCustomValidationList(String documentName) {
        List<CustomValidation> customValidationList = new ArrayList<>();
        if(DRIVING_LICENSE_FRONT.equalsIgnoreCase(documentName)){
            customValidationList = drivingLicenseCustomValidationsForAddressVerification;
        }else if (UTILITY_BILL.equalsIgnoreCase(documentName)){
            customValidationList = utilityBillCustomValidationsForAddressValidation;
        }
        return customValidationList;
    }
}
