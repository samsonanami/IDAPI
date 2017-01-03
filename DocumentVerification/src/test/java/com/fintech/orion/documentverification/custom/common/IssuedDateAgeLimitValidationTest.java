package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class IssuedDateAgeLimitValidationTest {
    private IssuedDateAgeLimitValidation issuedDateAgeLimitValidation = new IssuedDateAgeLimitValidation();

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataOfIssue;
    private ResourceName resourceName;

    @Before
    public void setup()throws Exception{
        ocrFieldDataOfIssue = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_within_age_limit()throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2004");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.2004");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateofBirthString("25.07.1974");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_below_than_age_limit()throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2014");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.1978");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateofBirthString("25.07.1974");
        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_greater_than_age_limit()throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2004");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.2004");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateofBirthString("25.07.1904");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_within_date_limit_and_dates_in_different_format() throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("20 JAN /JAN 04");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("20 JAN /JAN 04");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateofBirthString("25.07.1974");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test(expected = CustomValidationException.class)
    public void should_throw_CustomValidationException_false_if_date_format_is_not_supported()throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25/07/1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateofBirthString("25.07.1974");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }
}
