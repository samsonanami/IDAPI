package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
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
public class DateOfIsssueEndYearValidationTest {
    private DateOfIssueEndYearValidation dateOfIssueEndYearValidation = new DateOfIssueEndYearValidation();

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataDateOfIssue;
    private ResourceName resourceName;

    @Before
    public void setup() throws Exception {
        ocrFieldDataDateOfIssue = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
    }

    @Test
    public void should_return_true_if_issued_date_in_every_document_is_within_year_limit() throws Exception {
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2014");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.2014");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataDateOfIssue.setId("date_of_issue");
        ocrFieldDataDateOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList();
        fieldDataList.add(ocrFieldDataDateOfIssue);

        ocrResponse.setData(fieldDataList);

        dateOfIssueEndYearValidation.setOcrExtractionFieldName("date_of_issue");
        dateOfIssueEndYearValidation.setValidYearCount(10);


        ValidationData verificationData = dateOfIssueEndYearValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_issued_date_in_every_document_is_older_than_year_limit() throws Exception {
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2014");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.2004");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataDateOfIssue.setId("date_of_issue");
        ocrFieldDataDateOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataDateOfIssue);

        ocrResponse.setData(fieldDataList);

        dateOfIssueEndYearValidation.setOcrExtractionFieldName("date_of_issue");
        dateOfIssueEndYearValidation.setValidYearCount(10);

        ValidationData verificationData = dateOfIssueEndYearValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_issued_date_in_every_document_is_within_limit_and_dates_in_different_format() throws Exception {
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("20 JAN /JAN 07");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("20 JAN /JAN 07");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataDateOfIssue.setId("date_of_issue");
        ocrFieldDataDateOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataDateOfIssue);

        ocrResponse.setData(fieldDataList);

        dateOfIssueEndYearValidation.setOcrExtractionFieldName("date_of_issue");
        dateOfIssueEndYearValidation.setValidYearCount(10);


        ValidationData verificationData = dateOfIssueEndYearValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_throw_CustomValidationException_false_if_date_format_is_not_supported() throws Exception {
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25/07/1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataDateOfIssue.setId("date_of_issue");
        ocrFieldDataDateOfIssue.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataDateOfIssue);

        ocrResponse.setData(fieldDataList);

        dateOfIssueEndYearValidation.setOcrExtractionFieldName("date_of_issue");
        dateOfIssueEndYearValidation.setValidYearCount(10);

        ValidationData verificationData = dateOfIssueEndYearValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }
}
