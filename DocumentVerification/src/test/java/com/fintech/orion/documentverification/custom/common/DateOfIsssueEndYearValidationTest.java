package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class DateOfIsssueEndYearValidationTest {
    @InjectMocks
    private DateOfIssueEndYearValidation dateOfIssueEndYearValidation;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataDateOfIssue;
    private ResourceName resourceName;
    private SimpleDateFormat dateFormat;
    private String templateCategory;

    @Mock
    private DateDecoder dateDecoder;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataDateOfIssue = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        templateCategory = "TODO:";
    }

    @Test
    public void should_return_true_if_issued_date_in_every_document_is_within_year_limit() throws Exception {
        Mockito.when(dateDecoder.decodeDate("25.07.2014", templateCategory)).thenReturn(dateFormat.parse("07/25/2014"));

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
        Mockito.when(dateDecoder.decodeDate("25.07.2014", templateCategory)).thenReturn(dateFormat.parse("07/25/2014"));
        Mockito.when(dateDecoder.decodeDate("25.07.2014", templateCategory)).thenReturn(dateFormat.parse("07/25/2004"));

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
        Mockito.when(dateDecoder.decodeDate("20 JAN /JAN 07", templateCategory)).thenReturn(dateFormat.parse("01/20/2007"));


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

        Mockito.when(dateDecoder.decodeDate("25/07/1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));

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
