package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
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
public class IssuedDateAgeLimitValidationTest {
    @InjectMocks
    private IssuedDateAgeLimitValidation issuedDateAgeLimitValidation;

    @Mock
    private DateDecoder dateDecoder;

    @Mock
    private OcrResponseReader responseReader;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataOfIssue;
    private OcrFieldData ocrFieldDataOfBirth;
    private ResourceName resourceName;
    private SimpleDateFormat dateFormat;
    private String templateCategory;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataOfIssue = new OcrFieldData();
        ocrFieldDataOfBirth = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        templateCategory = null;
        issuedDateAgeLimitValidation.setResponseReader(responseReader);
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_within_age_limit() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.07.2004", templateCategory)).thenReturn(dateFormat.parse("07/25/2004"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2004");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.2004");


        List<OcrFieldValue> issuedDateFieldValueList = new ArrayList<>();
        issuedDateFieldValueList.add(passportValue);
        issuedDateFieldValueList.add(dlFrontValue);


        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(issuedDateFieldValueList);

        OcrFieldValue passportDateOfBirthValue = new OcrFieldValue();
        passportDateOfBirthValue.setId("passport##date_of_birth");
        passportDateOfBirthValue.setValue("25.07.1974");

        List<OcrFieldValue> dateOfBirtheFieldValueList = new ArrayList<>();
        dateOfBirtheFieldValueList.add(passportDateOfBirthValue);

        ocrFieldDataOfBirth.setId("date_of_birth");
        ocrFieldDataOfBirth.setValue(dateOfBirtheFieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);
        fieldDataList.add(ocrFieldDataOfBirth);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateOfBirthOcrExtractionField("date_of_birth");
        resourceName.setName("passport");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_below_than_age_limit() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.07.1978", templateCategory)).thenReturn(dateFormat.parse("07/25/1978"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.1978");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.1978");


        List<OcrFieldValue> issuedDateFieldValueList = new ArrayList<>();
        issuedDateFieldValueList.add(passportValue);
        issuedDateFieldValueList.add(dlFrontValue);


        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(issuedDateFieldValueList);

        OcrFieldValue passportDateOfBirthValue = new OcrFieldValue();
        passportDateOfBirthValue.setId("passport##date_of_birth");
        passportDateOfBirthValue.setValue("25.07.1974");

        List<OcrFieldValue> dateOfBirtheFieldValueList = new ArrayList<>();
        dateOfBirtheFieldValueList.add(passportDateOfBirthValue);

        ocrFieldDataOfBirth.setId("date_of_birth");
        ocrFieldDataOfBirth.setValue(dateOfBirtheFieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);
        fieldDataList.add(ocrFieldDataOfBirth);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateOfBirthOcrExtractionField("date_of_birth");
        resourceName.setName("passport");
        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_greater_than_age_limit() throws Exception {
        Mockito.when(dateDecoder.decodeDate("25.07.2004", templateCategory)).thenReturn(dateFormat.parse("07/25/2004"));
        Mockito.when(dateDecoder.decodeDate("25.07.1904", templateCategory)).thenReturn(dateFormat.parse("07/25/1904"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("25.07.2004");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("25.07.2004");


        List<OcrFieldValue> issuedDateFieldValueList = new ArrayList<>();
        issuedDateFieldValueList.add(passportValue);
        issuedDateFieldValueList.add(dlFrontValue);


        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(issuedDateFieldValueList);

        OcrFieldValue passportDateOfBirthValue = new OcrFieldValue();
        passportDateOfBirthValue.setId("passport##date_of_birth");
        passportDateOfBirthValue.setValue("25.07.1904");

        List<OcrFieldValue> dateOfBirtheFieldValueList = new ArrayList<>();
        dateOfBirtheFieldValueList.add(passportDateOfBirthValue);

        ocrFieldDataOfBirth.setId("date_of_birth");
        ocrFieldDataOfBirth.setValue(dateOfBirtheFieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);
        fieldDataList.add(ocrFieldDataOfBirth);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateOfBirthOcrExtractionField("date_of_birth");
        resourceName.setName("passport");
        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_within_date_limit_and_dates_in_different_format() throws Exception {

        Mockito.when(dateDecoder.decodeDate("20 JAN /JAN 04", templateCategory)).thenReturn(dateFormat.parse("01/20/2004"));
        Mockito.when(dateDecoder.decodeDate("20.01.2004", templateCategory)).thenReturn(dateFormat.parse("01/20/2004"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));

        //////////////////////////////////////////////////
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("20 JAN /JAN 04");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("20.01.2004");


        List<OcrFieldValue> issuedDateFieldValueList = new ArrayList<>();
        issuedDateFieldValueList.add(passportValue);
        issuedDateFieldValueList.add(dlFrontValue);


        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(issuedDateFieldValueList);

        OcrFieldValue passportDateOfBirthValue = new OcrFieldValue();
        passportDateOfBirthValue.setId("passport##date_of_birth");
        passportDateOfBirthValue.setValue("25.07.1974");

        List<OcrFieldValue> dateOfBirtheFieldValueList = new ArrayList<>();
        dateOfBirtheFieldValueList.add(passportDateOfBirthValue);

        ocrFieldDataOfBirth.setId("date_of_birth");
        ocrFieldDataOfBirth.setValue(dateOfBirtheFieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);
        fieldDataList.add(ocrFieldDataOfBirth);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateOfBirthOcrExtractionField("date_of_birth");
        resourceName.setName("passport");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_throw_CustomValidationException_false_if_date_format_is_not_supported() throws Exception {

        Mockito.when(dateDecoder.decodeDate("20/01/2004", templateCategory)).thenThrow(new DateDecoderException("Unsupported date format"));
        Mockito.when(dateDecoder.decodeDate("20.01.2004", templateCategory)).thenReturn(dateFormat.parse("01/20/2004"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));


        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_issue");
        passportValue.setValue("20/01/2004");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_issue");
        dlFrontValue.setValue("20.01.2004");


        List<OcrFieldValue> issuedDateFieldValueList = new ArrayList<>();
        issuedDateFieldValueList.add(passportValue);
        issuedDateFieldValueList.add(dlFrontValue);


        ocrFieldDataOfIssue.setId("date_of_issue");
        ocrFieldDataOfIssue.setValue(issuedDateFieldValueList);

        OcrFieldValue passportDateOfBirthValue = new OcrFieldValue();
        passportDateOfBirthValue.setId("passport##date_of_birth");
        passportDateOfBirthValue.setValue("25.07.1974");

        List<OcrFieldValue> dateOfBirtheFieldValueList = new ArrayList<>();
        dateOfBirtheFieldValueList.add(passportDateOfBirthValue);

        ocrFieldDataOfBirth.setId("date_of_birth");
        ocrFieldDataOfBirth.setValue(dateOfBirtheFieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataOfIssue);
        fieldDataList.add(ocrFieldDataOfBirth);

        ocrResponse.setData(fieldDataList);

        issuedDateAgeLimitValidation.setOcrExtractionFieldName("date_of_issue");
        issuedDateAgeLimitValidation.setMinimumAge(18);
        issuedDateAgeLimitValidation.setMaximumAge(90);
        issuedDateAgeLimitValidation.setDateOfBirthOcrExtractionField("date_of_birth");
        resourceName.setName("passport");

        ValidationData verificationData = issuedDateAgeLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }
}
