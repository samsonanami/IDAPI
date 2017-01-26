package com.fintech.orion.documentverification.factory;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.documentverification.common.checkdigit.PassportCheckDigitFormation;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.mrz.DrivingLicenseMZRDecodingStrategy;
import com.fintech.orion.documentverification.common.mrz.PassportMRZDecodingStrategy;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/26/16.
 */
public class IdentificationDocumentFullVerification implements DocumentVerification {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentificationDocumentFullVerification.class);

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private PassportMRZDecodingStrategy passportMRZDecodingStrategy;

    @Autowired
    private DrivingLicenseMZRDecodingStrategy drivingLicenseMRZDecodingStrategy;

    @Autowired
    private PassportCheckDigitFormation passportCheckDigitFormation;

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
            if (idVerificationResource == null && (resource.getResourceName().getName().equalsIgnoreCase("passport")
                    || resource.getResourceName().getName().equalsIgnoreCase("drivingLicenseFront"))) {
                idVerificationResource = resource;
            }
        }
        ResourceName resourceName = new ResourceName();
        if (idVerificationResource != null) {
            resourceName = idVerificationResource.getResourceName();
        }
        List<Object> idDocFullValidationList = new ArrayList();
        ValidationData errorDataSet = new ValidationData();
        errorDataSet.setRemarks("");
        errorDataSet.setId("critical_error_set");
        LOGGER.debug("Starting custom validation with resource name {} and ocr response {}", resourceName, ocrResponse);
        for (CustomValidation validation : getCustomValidationList()) {
            try {
                ValidationData validationData = validation.validate(resourceName, ocrResponse);
                idDocFullValidationList.add(validationData);
                if (!validationData.getValidationStatus() && validation.isCriticalValidation()) {
                    errorDataSet.setRemarks(errorDataSet.getRemarks() + validationData.getRemarks());
                }
            } catch (CustomValidationException e) {
                LOGGER.error("Unable to execute custom validation {}", validation);
            }
        }

        idDocFullValidationList.add(errorDataSet);

        return idDocFullValidationList;
    }

    private List<CustomValidation> getCustomValidationList() {
        return idDocCustomValidations;
    }

/*
    private ValidationData getDocumentNumber(OcrResponse ocrResponse, String resourceName){
        ValidationData validationData = new ValidationData();
        validationData.setId("Document Number");
        String documentNumberField = "";
        if (resourceName.equalsIgnoreCase("passport")){
            documentNumberField = "passport_number";
        }
        OcrFieldData fieldData = getFieldDataById(documentNumberField, ocrResponse);
        OcrFieldValue value  = fieldData.getValue().get(0);
        validationData.setValue(value.getValue());
        validationData.setOcrConfidence(value.getConfidence());
        validationData.setRemarks("");
        return validationData;
    }

    private ValidationData getDocumentExpiredDate(OcrResponse ocrResponse, String resourceName){
        ValidationData validationData = new ValidationData();
        validationData.setId("Document Expired Date");
        OcrFieldData fieldData = getFieldDataById("date_of_expiry", ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(resourceName+"##date_of_expiry", fieldData);
        validationData.setValue(fieldValue.getValue());
        validationData.setOcrConfidence(fieldValue.getConfidence());
        validationData.setRemarks("Document is expired");
        return validationData;
    }



    private ValidationData documentCheckDigitValidation(OcrResponse ocrResponse, String resourceName){
        ValidationData validationData = new ValidationData();
        validationData.setId("Check Digit Validation");


        String completeMRZLine = "";
        if(resourceName.equalsIgnoreCase("passport")){
            OcrFieldData mrzLine1 = getFieldDataById("MRZ_line1", ocrResponse);
            OcrFieldValue mrzLine1FieldValue = getFieldValueById(resourceName+"##MRZ_line1", mrzLine1);
            OcrFieldData mrzLine2 = getFieldDataById("MRZ_line2", ocrResponse);
            OcrFieldValue mrzLine2FieldValue = getFieldValueById(resourceName+"##MRZ_line2", mrzLine2);
            completeMRZLine = mrzLine1FieldValue.getValue()+mrzLine2FieldValue.getValue();
        }else if (resourceName.equalsIgnoreCase("drivingLicenseFront")){
            validationData.setValue("Failed");
            validationData.setRemarks("Check digit validation is not available for driving license");
        }
        MRZDecodeResults mrzDecodeResults = new MRZDecodeResults();
        try {
            if (resourceName.equalsIgnoreCase("passport")){
                mrzDecodeResults = passportMRZDecodingStrategy.decode(completeMRZLine);
            }else if(resourceName.equalsIgnoreCase("drivingLicenseFront")){
                mrzDecodeResults = drivingLicenseMRZDecodingStrategy.decode(completeMRZLine);
            }
            CheckDigitResults checkDigitResults = passportCheckDigitFormation.calculateCheckDigit(completeMRZLine);
            if (checkDigitResults.getCheckdigitPraseOne().equals(mrzDecodeResults.getCheckDigitPhraseOne()) &&
                    checkDigitResults.getCheckdigitPraseTwo().equals(mrzDecodeResults.getCheckDigitPhraseTwo()) &&
                    checkDigitResults.getCheckdigitPraseThree().equals(mrzDecodeResults.getCheckDigitPhraseThree()) &&
                    checkDigitResults.getCheckdigitPraseFour().equals(mrzDecodeResults.getCheckDigitPhraseFour()) &&
                    checkDigitResults.getCheckdigitPraseFive().equals(mrzDecodeResults.getCheckDigitPhraseFive())){
                validationData.setValue("Passed");
                validationData.setRemarks("All check digit validations passed");
            }else {
                validationData.setValue("Failed");
                validationData.setRemarks("One or more check digit validations failed");
            }
        } catch (CheckDigitFormationException e) {
            validationData.setValue("Failed");
            validationData.setRemarks("Unable to complete the check digit validation");
            LOGGER.warn("Unable to generate check digit from the mrz {} ", completeMRZLine, e);
        } catch (PassportMRZDecodeException e) {
            validationData.setValue("Failed");
            validationData.setRemarks("Unable to complete the check digit validation");
            LOGGER.warn("Error occurred while decoding passport mrz {}", completeMRZLine, e);
        } catch (DrivingLicenseMRZDecodingException e) {
            validationData.setValue("Failed");
            validationData.setRemarks("Unable to complete the check digit validation");
            LOGGER.warn("Error occurred while decoding driving license mrz {}", completeMRZLine, e);
        }


        return validationData;
    }
    */
}
