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
 * Created by sasitha on 12/29/16.
 */
public class MinimumAgeValidationTest {

    @InjectMocks
    private MinimumAgeValidation minimumAgeValidation;

    @Mock
    DateDecoder dateDecoder;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSex;
    private ResourceName resourceName;
    private SimpleDateFormat dateFormat;
    private String templateCategory;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataSex = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        templateCategory = "TODO:";
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_above_minimum_age() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25.07.1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("date_of_birth");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);

        minimumAgeValidation.setOcrExtractionFieldName("date_of_birth");
        minimumAgeValidation.setMinimumAge(18);

        ValidationData verificationData = minimumAgeValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_age_in_any_document_less_than_minimum_age() throws Exception {
        Mockito.when(dateDecoder.decodeDate("25.07.2014", templateCategory)).thenReturn(dateFormat.parse("07/25/2014"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25.07.2014");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("date_of_birth");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);

        minimumAgeValidation.setOcrExtractionFieldName("date_of_birth");
        minimumAgeValidation.setMinimumAge(18);

        ValidationData verificationData = minimumAgeValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_age_in_every_document_is_above_minimum_age_and_dates_in_different_format() throws Exception {
        Mockito.when(dateDecoder.decodeDate("20 JAN /JAN 59", templateCategory)).thenReturn(dateFormat.parse("01/20/1959"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("20 JAN /JAN 59");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("20 JAN /JAN 59");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("date_of_birth");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);

        minimumAgeValidation.setOcrExtractionFieldName("date_of_birth");
        minimumAgeValidation.setMinimumAge(18);

        ValidationData verificationData = minimumAgeValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_date_format_is_not_supported() throws Exception {
        Mockito.when(dateDecoder.decodeDate("25/07/1974", templateCategory)).thenThrow(new DateDecoderException("Unsupported date"));
        Mockito.when(dateDecoder.decodeDate("25.07.1974", templateCategory)).thenReturn(dateFormat.parse("07/25/1974"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25/07/1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("date_of_birth");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);

        minimumAgeValidation.setOcrExtractionFieldName("date_of_birth");
        minimumAgeValidation.setMinimumAge(18);

        ValidationData verificationData = minimumAgeValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }
}
