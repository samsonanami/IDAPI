package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.Address.AddressCompare;
import com.fintech.orion.documentverification.common.Address.AddressCompareResult;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/29/16.
 */
public class AddressValidationTest {

    @InjectMocks
    private AddressValidation addressValidation;

    @Mock
    private AddressCompare addressComparator;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSex;
    private ResourceName resourceName;
    private AddressCompareResult addressCompareResult;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataSex = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        addressCompareResult = new AddressCompareResult();
        addressValidation.setOcrExtractionFieldName("address");

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##address");
        passportValue.setValue("42, PYMMES GREEN ROAD, LONDON, N11 1BY");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##address");
        dlFrontValue.setValue("42, PYMMES GREEN ROAD, LONDON, N11 1BY");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("address");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);

        ocrResponse.setData(fieldDataList);
    }

    @Test
    public void should_return_tru_if_both_address_are_matching()throws Exception{
        addressCompareResult.setResult(true);
        Mockito.when(addressComparator.compare(Matchers.anyString(), Matchers.anyString())).thenReturn(addressCompareResult);

        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
        assertTrue(validationData.getValidationStatus());

    }

    @Test
    public void should_return_false_if_address_are_not_matching()throws Exception{
        addressCompareResult.setResult(false);
        Mockito.when(addressComparator.compare(Matchers.anyString(), Matchers.anyString())).thenReturn(addressCompareResult);
        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

    @Test(expected = CustomValidationException.class)
    public void should_throw_CustomValidationException_if_addressFieldName_is_not_set()throws Exception{
        addressValidation.setOcrExtractionFieldName(null);
        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
    }

}