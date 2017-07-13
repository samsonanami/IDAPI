package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.address.AddressCompare;
import com.fintech.orion.documentverification.common.address.AddressCompareResult;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
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
 * Created by sasitha on 12/29/16.
 */
public class AddressValidationTest {

    @InjectMocks
    private AddressValidation addressValidation;

    @Mock
    private AddressCompare addressComparator;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldAddressLine1;
    private OcrFieldData ocrFieldAddressLine2;
    private OcrFieldData ocrFieldAddressLine3;
    private ResourceName resourceName;
    private AddressCompareResult addressCompareResult;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldAddressLine1 = new OcrFieldData();
        ocrFieldAddressLine2 = new OcrFieldData();
        ocrFieldAddressLine3 = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        addressCompareResult = new AddressCompareResult();
        addressValidation.setOcrFieldBase("address_line");
        addressValidation.setAddressLineCount(3);
        addressValidation.setSuccessRemarksMessage("address validation completed");
        addressValidation.setFailedRemarksMessage("address validation failed");


        OcrFieldValue utilityBillLine1 = new OcrFieldValue();
        utilityBillLine1.setId("utilityBill##address_line1##NPP");
        utilityBillLine1.setValue("42 PYMMES GREEN ROAD");

        OcrFieldValue dlFrontLine1 = new OcrFieldValue();
        dlFrontLine1.setId("drivingLicenseFront##address_line1##NPP");
        dlFrontLine1.setValue("42, PYMMES GREEN ROAD, LONDON, N11 1BY");

        List<OcrFieldValue> addressLine1FieldValueList = new ArrayList<>();
        addressLine1FieldValueList.add(utilityBillLine1);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        List<OcrFieldValue> addressLine2FieldValueList = new ArrayList<>();
        OcrFieldValue utilityBillLine2 = new OcrFieldValue();
        utilityBillLine2.setId("utilityBill##address_line2##NPP");
        utilityBillLine2.setValue("LONDON");
        addressLine2FieldValueList.add(utilityBillLine2);

        ocrFieldAddressLine2.setId("address_line2");
        ocrFieldAddressLine2.setValue(addressLine2FieldValueList);

        List<OcrFieldValue> addressLine3FieldValueList = new ArrayList<>();
        OcrFieldValue utilityBillLine3 = new OcrFieldValue();
        utilityBillLine3.setId("utilityBill##address_line3##NPP");
        utilityBillLine3.setValue("N11 1BY");
        addressLine3FieldValueList.add(utilityBillLine3);

        ocrFieldAddressLine3.setId("address_line3");
        ocrFieldAddressLine3.setValue(addressLine3FieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        addressValidation.setAddressBuilder(new AddressBuilder());
    }

    @Test
    public void should_return_true_if_both_address_are_matching() throws Exception {
        addressCompareResult.setResult(true);
        Mockito.when(addressComparator.compare(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(addressCompareResult);

        resourceName.setName("utilityBill");
        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
        boolean actual = validationData.getValidationStatus();
        assertTrue(actual);

    }

    @Test
    public void should_return_false_if_address_are_not_matching() throws Exception {
        addressCompareResult.setResult(false);
        resourceName.setName("utilityBill");
        Mockito.when(addressComparator.compare(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(addressCompareResult);
        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

    @Test(expected = CustomValidationException.class)
    public void should_throw_CustomValidationException_if_addressFieldBase_is_not_set() throws Exception {
        addressValidation.setOcrFieldBase(null);
        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
    }

    @Test
    public void should_return_false_if_address_comparator_throws_an_exception() throws Exception {
        addressCompareResult.setResult(false);
        resourceName.setName("utilityBill");
        Mockito.when(addressComparator.compare(Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new AddressValidatingException("Failed to validate address"));
        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_there_are_no_address_for_given_resource_name() throws Exception {
        resourceName.setName("passport");
        OcrFieldValue utilityBillLine1 = new OcrFieldValue();
        utilityBillLine1.setId("utilityBill##address_line1");

        OcrFieldValue dlFrontLine1 = new OcrFieldValue();
        dlFrontLine1.setId("drivingLicenseFront##address_line1");

        List<OcrFieldValue> addressLine1FieldValueList = new ArrayList<>();
        addressLine1FieldValueList.add(utilityBillLine1);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        List<OcrFieldValue> addressLine2FieldValueList = new ArrayList<>();
        OcrFieldValue utilityBillLine2 = new OcrFieldValue();
        utilityBillLine2.setId("utilityBill##address_line2");
        addressLine2FieldValueList.add(utilityBillLine2);

        ocrFieldAddressLine2.setId("address_line2");
        ocrFieldAddressLine2.setValue(addressLine2FieldValueList);

        List<OcrFieldValue> addressLine3FieldValueList = new ArrayList<>();
        OcrFieldValue utilityBillLine3 = new OcrFieldValue();
        utilityBillLine3.setId("utilityBill##address_line3");
        addressLine3FieldValueList.add(utilityBillLine3);

        ocrFieldAddressLine3.setId("address_line3");
        ocrFieldAddressLine3.setValue(addressLine3FieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        ValidationData validationData = addressValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

}