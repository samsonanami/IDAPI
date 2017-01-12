package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/29/16.
 */
public class SimpleStringFieldValidatorTest {
    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSex;
    private ResourceName resourceName;
    private SimpleStringFieldValidator simpleStringFieldValidator;
    @Before
    public void setUp() throws Exception {
        simpleStringFieldValidator = new SimpleStringFieldValidator();
        ocrFieldDataSex = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        simpleStringFieldValidator.setVerificationDisplayName("Simple Verification");
    }


    @Test
    public void should_return_true_if_all_the_document_has_same_sex_mentioned()throws Exception{
        simpleStringFieldValidator.setOcrExtractionFieldName("sex");

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##sex");
        passportValue.setValue("M");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##sex");
        dlFrontValue.setValue("M");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("sex");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);
        ValidationData validationData = simpleStringFieldValidator.validate(resourceName, ocrResponse);
        assertTrue(validationData.getValidationStatus());
    }

    @Test(expected = CustomValidationException.class)
    public void should_CustomValidationException_if_stringField_is_null() throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##surname");
        passportValue.setValue("M");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##surname");
        dlFrontValue.setValue("M");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("surname");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);
        ValidationData validationData = simpleStringFieldValidator.validate(resourceName, ocrResponse);
    }

    @Test
    public void should_return_false_if_valise_are_not_matching() throws Exception{
        simpleStringFieldValidator.setOcrExtractionFieldName("surname");
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##surname");
        passportValue.setValue("M");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##surname");
        dlFrontValue.setValue("D");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("surname");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);
        ValidationData validationData = simpleStringFieldValidator.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

}