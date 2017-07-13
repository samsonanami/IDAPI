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
import java.util.stream.Collectors;

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

    private List<OcrFieldValue> filterFieldDataValueList(OcrFieldValue base, List<OcrFieldValue> values) {

        String idOfTheFirstObject = base.getId();
        String prefixOfFirstObjectId = idOfTheFirstObject.substring(0, idOfTheFirstObject.lastIndexOf('#') + 1);
        String suffixOfFirstObjectId = idOfTheFirstObject.split("##")[2].toString();

        if (suffixOfFirstObjectId.equals("PP")) {
            String removeObjectId = prefixOfFirstObjectId.concat("NPP");
            return values.stream().filter(p -> !p.getId().equals(removeObjectId) && !p.getId().equals(idOfTheFirstObject)).collect(Collectors.toList());

        } else if (suffixOfFirstObjectId.equals("NPP")) {
            String removeObjectId = prefixOfFirstObjectId.concat("PP");
            return values.stream().filter(p -> !p.getId().equals(removeObjectId) && !p.getId().equals(idOfTheFirstObject)).collect(Collectors.toList());
        }
        return values;
    }

    private ValidationData validateDateOfBirth(List<OcrFieldValue> values,
                                               OcrResponse ocrResponse) throws CustomValidationException, TranslatorException {
        ValidationData validationData = new ValidationData();
        if (!values.isEmpty() && values.size()>1) {

            for (OcrFieldValue ocrFieldValue : values.subList(0, 2)) {

                String firstDateOfBirth = ocrFieldValue.getValue();
                String templateCategory = getTemplateCategory(ocrFieldValue.getId(), ocrResponse);
                Date dateOfBirth = (Date) ocrValueTranslator.translate(firstDateOfBirth, templateCategory);

                List<OcrFieldValue> filteredOcrFieldValueList = filterFieldDataValueList(ocrFieldValue, values);
                validationData = compareRestOfTheDatesWithBaseDate(dateOfBirth, filteredOcrFieldValueList, ocrResponse);

                if (validationData.getValidationStatus()) {
                    return validationData;
                }

            }

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
            } else {
                validationData.setValidationStatus(true);
                validationData.setValue(value.getValue());
                validationData.setRemarks(getSuccessRemarksMessage());
                break;
            }
        }
        return validationData;
    }
}
