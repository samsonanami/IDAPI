package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/29/16.
 *
 */
public class GenderValidationTest {


    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSex;
    private ResourceName resourceName;
    private GenderValidation genderValidation;
    @Before
    public void setUp() throws Exception {
        genderValidation = new GenderValidation();
        ocrFieldDataSex = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();

        genderValidation.setOcrExtractionFieldName("sex");
        genderValidation.setFailedRemarksMessage("Faild");
        genderValidation.setSuccessRemarksMessage("Success");
    }

    @Test
    public void should_return_true_if_all_the_document_has_same_sex_mentioned()throws Exception{

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

        ValidationData validationData = genderValidation.validate(resourceName, ocrResponse);
        assertTrue(validationData.getValidationStatus());
        assertEquals(validationData.getValue(), "MALE");
    }

    @Test
    public void should_return_false_if_document_gender_is_not_matching()throws Exception{
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##sex");
        passportValue.setValue("Female");

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

        ValidationData validationData = genderValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
        assertEquals(validationData.getValue(), "FEMALE");
    }

}