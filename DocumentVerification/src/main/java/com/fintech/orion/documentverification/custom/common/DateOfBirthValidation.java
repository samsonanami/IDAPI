package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.documentverification.strategy.OperationDateComparator;
import com.fintech.orion.documentverification.translator.OcrValueTranslator;
import com.fintech.orion.documentverification.translator.OcrValueTranslatorFactory;
import com.fintech.orion.documentverification.translator.exception.TranslatorException;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class DateOfBirthValidation extends ValidationHelper implements CustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateOfBirthValidation.class);
    @Autowired
    private OperationDateComparator dateComparator;

    @Autowired
    private OcrValueTranslatorFactory ocrValueTranslatorFactory;

    OcrValueTranslator ocrValueTranslator;
    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        ocrValueTranslator = ocrValueTranslatorFactory.getOcrValueTranslator(getOcrExtractionFieldName());
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData = validateDateOfBirth(fieldData.getValue(), ocrResponse);
            } catch (TranslatorException e) {
                validationData.setValidationStatus(false);
                validationData.setRemarks(getFailedRemarksMessage());
                LOGGER.error("Error while translating validating date of birth ", e);
            }
        }
        validationData.setCriticalValidation(isCriticalValidation());
        validationData.setId("Date of Birth Validation");
        return validationData;
    }

    private ValidationData validateDateOfBirth(List<OcrFieldValue> values,
                                               OcrResponse ocrResponse) throws CustomValidationException, TranslatorException {
        ValidationData validationData = new ValidationData();
        if (!values.isEmpty()) {
            String firstDateOfBirth = values.iterator().next().getValue();
            String templateCategory = getTemplateCategory(values.iterator().next().getId(), ocrResponse);
            Date dateOfBirth = (Date) ocrValueTranslator.translate(firstDateOfBirth, templateCategory);
            validationData = compareRestOfTheDatesWithBaseDate(dateOfBirth, values, ocrResponse);
        } else {
            validationData.setValidationStatus(false);
            validationData.setRemarks("Not Enough data to complete the validation. Need two or more date of births from" +
                    "multiple documents to complete this verification.");
        }
        return validationData;
    }

    private ValidationData compareRestOfTheDatesWithBaseDate(Date base, List<OcrFieldValue> values,
                                                             OcrResponse ocrResponse) throws TranslatorException {
        ValidationData validationData = new ValidationData();


        for (OcrFieldValue value : values) {
            String templateCategory = getTemplateCategory(value.getId(), ocrResponse);
            Date dateOfBirth = (Date) ocrValueTranslator.translate(value.getValue(), templateCategory);
            if (!dateComparator.doDataValidationOperation(base, dateOfBirth, templateCategory).isStatus()) {
                validationData.setValidationStatus(false);
                validationData.setValue(value.getValue());
                validationData.setRemarks(getFailedRemarksMessage());
                break;
            } else {
                validationData.setValidationStatus(true);
                validationData.setValue(value.getValue());
                validationData.setRemarks(getSuccessRemarksMessage());
            }
        }
        return validationData;
    }
}
