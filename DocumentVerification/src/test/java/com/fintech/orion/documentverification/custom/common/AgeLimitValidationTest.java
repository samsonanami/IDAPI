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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class AgeLimitValidationTest {
    @InjectMocks
    private AgeLimitValidation ageLimitValidation;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDateOfBirth;
    private ResourceName resourceName;
    private DateFormat df;

    @Mock
    private OcrResponseReader ocrResponseReader;

    @Mock
    private DateDecoder dateDecoder;
    private String templateCategory;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDateOfBirth = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        df = new SimpleDateFormat("MM/dd/yyyy");
        templateCategory = null;
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_within_age_limit() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(df.parse("07/25/1974"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25.07.1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDateOfBirth.setId("date_of_birth");
        ocrFieldDateOfBirth.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDateOfBirth);

        ocrResponse.setData(fieldDataList);

        ageLimitValidation.setOcrExtractionFieldName("date_of_birth");
        ageLimitValidation.setMinimumAge(18);
        ageLimitValidation.setMaximumAge(90);
        ageLimitValidation.setResponseReader(ocrResponseReader);

        ValidationData verificationData = ageLimitValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_below_than_age_limit() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(df.parse("07/25/1974"));
        Mockito.when(dateDecoder.decodeDate("25.07.2014", templateCategory)).thenReturn(df.parse("07/25/2014"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25.07.2014");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDateOfBirth.setId("date_of_birth");
        ocrFieldDateOfBirth.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDateOfBirth);

        ocrResponse.setData(fieldDataList);

        ageLimitValidation.setOcrExtractionFieldName("date_of_birth");
        ageLimitValidation.setMinimumAge(18);
        ageLimitValidation.setMaximumAge(90);

        ValidationData verificationData = ageLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_greater_than_age_limit() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.07.1914", templateCategory)).thenReturn(df.parse("07/25/1914"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(df.parse("07/25/1974"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25.07.1914");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDateOfBirth.setId("date_of_birth");
        ocrFieldDateOfBirth.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDateOfBirth);

        ocrResponse.setData(fieldDataList);

        ageLimitValidation.setOcrExtractionFieldName("date_of_birth");
        ageLimitValidation.setMinimumAge(18);
        ageLimitValidation.setMaximumAge(90);

        ValidationData verificationData = ageLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_within_date_limit_and_dates_in_different_format() throws Exception {

        Mockito.when(dateDecoder.decodeDate("20 JAN /JAN 59", templateCategory)).thenReturn(df.parse("01/20/1959"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("20 JAN /JAN 59");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("20 JAN /JAN 59");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDateOfBirth.setId("date_of_birth");
        ocrFieldDateOfBirth.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDateOfBirth);

        ocrResponse.setData(fieldDataList);

        ageLimitValidation.setOcrExtractionFieldName("date_of_birth");
        ageLimitValidation.setMinimumAge(18);
        ageLimitValidation.setMaximumAge(90);

        ValidationData verificationData = ageLimitValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_date_format_is_not_supported() throws Exception {
        Mockito.when(dateDecoder.decodeDate("25/07/1974", templateCategory)).thenThrow(new DateDecoderException("Un supported date format"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(df.parse("07/25/1974"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25/07/1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDateOfBirth.setId("date_of_birth");
        ocrFieldDateOfBirth.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDateOfBirth);

        ocrResponse.setData(fieldDataList);

        ageLimitValidation.setOcrExtractionFieldName("date_of_birth");
        ageLimitValidation.setMinimumAge(18);
        ageLimitValidation.setMaximumAge(90);

        ValidationData verificationData = ageLimitValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }
}
