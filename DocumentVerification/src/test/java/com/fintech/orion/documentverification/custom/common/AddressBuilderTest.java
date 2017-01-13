package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.address.AddressCompareResult;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sasitha on 1/3/17.
 */
public class AddressBuilderTest {
    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldAddressLine1;
    private OcrFieldData ocrFieldAddressLine2;
    private OcrFieldData ocrFieldAddressLine3;
    private ResourceName resourceName;
    private AddressCompareResult addressCompareResult;
    private AddressBuilder addressBuilder;
    private OcrFieldValue utilityBillLine1WithoutFlat = new OcrFieldValue();
    private OcrFieldValue utilityBillLine1WithFlat = new OcrFieldValue();
    private OcrFieldValue dlFrontLine1 = new OcrFieldValue();
    private List<OcrFieldValue> addressLine1FieldValueList = new ArrayList<>();
    private List<OcrFieldValue> addressLine2FieldValueList = new ArrayList<>();
    private OcrFieldValue utilityBillLine2 = new OcrFieldValue();
    private List<OcrFieldValue> addressLine3FieldValueList = new ArrayList<>();
    private OcrFieldValue utilityBillLine3 = new OcrFieldValue();
    private List<OcrFieldData> fieldDataList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        addressBuilder = new AddressBuilder();
        ocrFieldAddressLine1 = new OcrFieldData();
        ocrFieldAddressLine2 = new OcrFieldData();
        ocrFieldAddressLine3 = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        addressCompareResult = new AddressCompareResult();


        utilityBillLine1WithoutFlat.setId("utilityBill##address_line1");
        utilityBillLine1WithoutFlat.setValue("42 PYMMES GREEN ROAD");


        utilityBillLine1WithFlat.setId("utilityBill##address_line1");
        utilityBillLine1WithFlat.setValue("FLAT 42 PYMMES GREEN ROAD");


        dlFrontLine1.setId("drivingLicenseFront##address_line1");
        dlFrontLine1.setValue("42 PYMMES GREEN ROAD, LONDON, N11 1BY");


        utilityBillLine2.setId("utilityBill##address_line2");
        utilityBillLine2.setValue("LONDON");
        addressLine2FieldValueList.add(utilityBillLine2);

        ocrFieldAddressLine2.setId("address_line2");
        ocrFieldAddressLine2.setValue(addressLine2FieldValueList);


        utilityBillLine3.setId("utilityBill##address_line3");
        utilityBillLine3.setValue("N11 1BY");
        addressLine3FieldValueList.add(utilityBillLine3);

        ocrFieldAddressLine3.setId("address_line3");
        ocrFieldAddressLine3.setValue(addressLine3FieldValueList);


    }

    @Test
    public void should_return_build_address_as_a_single_line_without_flat_part_of_the_address() throws Exception {

        addressLine1FieldValueList.add(utilityBillLine1WithoutFlat);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        String addressFromUtilityBill = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse,
                "utilityBill", "address_line", 3);

        assertEquals("42, PYMMES GREEN ROAD, LONDON, N11 1BY", addressFromUtilityBill);
    }

    @Test
    public void should_return_build_address_as_a_single_line_with_flat_part_of_the_address() throws Exception {
        addressLine1FieldValueList.add(utilityBillLine1WithFlat);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        String addressFromUtilityBill = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse,
                "utilityBill", "address_line", 3);

        assertEquals("FLAT 42, PYMMES GREEN ROAD, LONDON, N11 1BY", addressFromUtilityBill);
    }

    @Test
    public void should_return_single_address_if_only_one_address_line_is_present() throws Exception {
        addressLine1FieldValueList.add(utilityBillLine1WithFlat);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        String addressFromUtilityBill = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse,
                "drivingLicenseFront", "address_line", 3);

        assertEquals("42, PYMMES GREEN ROAD, LONDON, N11 1BY", addressFromUtilityBill);
    }

    @Test
    public void should_return_empty_string_if_invalid_resource_name_given() throws Exception {
        addressLine1FieldValueList.add(utilityBillLine1WithFlat);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        String addressFromUtilityBill = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse,
                "invalid_resource", "address_line", 3);

        assertEquals("", addressFromUtilityBill);
    }

    @Test
    public void should_return_empty_string_if_invalid_ocr_field_base_is_given() throws Exception {
        addressLine1FieldValueList.add(utilityBillLine1WithFlat);
        addressLine1FieldValueList.add(dlFrontLine1);

        ocrFieldAddressLine1.setId("address_line1");
        ocrFieldAddressLine1.setValue(addressLine1FieldValueList);

        fieldDataList.add(ocrFieldAddressLine1);
        fieldDataList.add(ocrFieldAddressLine2);
        fieldDataList.add(ocrFieldAddressLine3);

        ocrResponse.setData(fieldDataList);

        String addressFromUtilityBill = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse,
                "utilityBill", "address", 3);

        assertEquals("", addressFromUtilityBill);
    }

    @Test
    public void should_return_empty_string_if_no_ocr_field_data_is_given() throws Exception {
        String addressFromUtilityBill = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse,
                "utilityBill", "address_line", 3);

        assertEquals("", addressFromUtilityBill);
    }

}