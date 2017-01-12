package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.strategy.OperationDateComparator;
import com.fintech.orion.documentverification.strategy.ValidationResult;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sasitha on 12/30/16.
 */
public class DateOfBirthValidationTest {

    @InjectMocks
    DateOfBirthValidation dateOfBirthValidation;

    @Mock
    private OperationDateComparator dateComparator;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSex;
    private ResourceName resourceName;
    private ValidationResult validationResult;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataSex = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        validationResult = new ValidationResult();
        dateOfBirthValidation.setOcrExtractionFieldName("date_of_birth");

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##date_of_birth");
        passportValue.setValue("25.07.1974");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##date_of_birth");
        dlFrontValue.setValue("25.07.1975");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("date_of_birth");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);
    }

    @Test
    public void should_return_true_if_all_document_have_same_date_of_birth() throws Exception {
        validationResult.setStatus(true);
        Mockito.when(dateComparator.doOperation(Matchers.anyString(), Matchers.anyString())).thenReturn(validationResult);

        ValidationData validationData = dateOfBirthValidation.validate(resourceName, ocrResponse);
        assertTrue(validationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_one_of_the_dates_are_not_matching() throws Exception {
        validationResult.setStatus(true);
        ValidationResult error = new ValidationResult(false);
        Mockito.when(dateComparator.doOperation("25.07.1974", "25.07.1974")).thenReturn(validationResult);
        Mockito.when(dateComparator.doOperation("25.07.1974", "25.07.1975")).thenReturn(error);

        ValidationData validationData = dateOfBirthValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

}