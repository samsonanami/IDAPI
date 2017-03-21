package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class ExpireDateValidation extends ValidationHelper implements CustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpireDateValidation.class);

    @Autowired
    private DateDecoder dateDecoder;
    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();

        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData = checkDocumentExpirationDate(fieldData, ocrResponse);
            } catch (DateDecoderException e) {
                LOGGER.warn("Error occurred while performing an expire date validation for ocr response {} on " +
                        "resource name {} {}", ocrResponse, resourceName.getName(), e);
                validationData.setValue(null);
                validationData.setOcrConfidence(null);
                validationData.setValidationStatus(false);
                validationData.setRemarks("Error occurred while performing . This is most likely " +
                        "due to an unsupported date format. Supported date formats are," +
                        "DD MM/MM YY or DD.MM.YYYY");
            }
        }
        if (validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Document Expiry Date Verification");
        return validationData;
    }

    private ValidationData checkDocumentExpirationDate(OcrFieldData ocrFieldData, OcrResponse ocrResponse) throws DateDecoderException {
        ValidationData validationData = new ValidationData();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            Date date = dateDecoder.decodeDate(fieldValue.getValue(), getTemplateCategory(fieldValue.getId(), ocrResponse));
            if (date.before(new Date())) {
                validationData.setRemarks(getDocumentNameFromOcrFieldValueId(fieldValue.getId()) + getFailedRemarksMessage());
                validationData.setValidationStatus(false);
                validationData.setValue(date.toString());
                break;
            } else {
                validationData.setValidationStatus(true);
            }
        }
        return validationData;
    }

}
