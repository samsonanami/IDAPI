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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class BillDateEndMonthValidationTest {
    private BillDateEndMonthValidation billDateEndMonthValidation = new BillDateEndMonthValidation();

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataBillDate;
    private ResourceName resourceName;

    @Before
    public void setup()throws Exception{
        ocrFieldDataBillDate = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
    }

    @Test
    public void should_return_true_if_bill_date_in_bill_is_within_month_limit()throws Exception{
        OcrFieldValue utilityBillValue = new OcrFieldValue();
        utilityBillValue.setId("utilitybill##bill_date");
        utilityBillValue.setValue("25.12.2016");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(utilityBillValue);


        ocrFieldDataBillDate.setId("bill_date");
        ocrFieldDataBillDate.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataBillDate);

        ocrResponse.setData(fieldDataList);

        billDateEndMonthValidation.setOcrExtractionFieldName("bill_date");
        billDateEndMonthValidation.setValidMonthCount(3);


        ValidationData verificationData = billDateEndMonthValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_bill_date_in_bill_is_older_than_month_limit()throws Exception{
        OcrFieldValue utilityBillValue = new OcrFieldValue();
        utilityBillValue.setId("utilitybill##bill_date");
        utilityBillValue.setValue("25.07.2014");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(utilityBillValue);


        ocrFieldDataBillDate.setId("bill_date");
        ocrFieldDataBillDate.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataBillDate);

        ocrResponse.setData(fieldDataList);

        billDateEndMonthValidation.setOcrExtractionFieldName("bill_date");
        billDateEndMonthValidation.setValidMonthCount(3);

        ValidationData verificationData = billDateEndMonthValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_bill_date_in_every_bill_is_within_limit_and_dates_in_different_format() throws Exception{
        OcrFieldValue utilityBillValue = new OcrFieldValue();
        utilityBillValue.setId("utilitybill##bill_date");
        utilityBillValue.setValue("25 DEC /DEC 16");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(utilityBillValue);


        ocrFieldDataBillDate.setId("bill_date");
        ocrFieldDataBillDate.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataBillDate);

        ocrResponse.setData(fieldDataList);

        billDateEndMonthValidation.setOcrExtractionFieldName("bill_date");
        billDateEndMonthValidation.setValidMonthCount(3);




        ValidationData verificationData = billDateEndMonthValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test(expected = CustomValidationException.class)
    public void should_throw_CustomValidationException_false_if_date_format_is_not_supported()throws Exception{
        OcrFieldValue utilityBillValue = new OcrFieldValue();
        utilityBillValue.setId("utilitybill##bill_date");
        utilityBillValue.setValue("25/07/1974");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(utilityBillValue);


        ocrFieldDataBillDate.setId("bill_date");
        ocrFieldDataBillDate.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataBillDate);

        ocrResponse.setData(fieldDataList);

        billDateEndMonthValidation.setOcrExtractionFieldName("bill_date");
        billDateEndMonthValidation.setValidMonthCount(3);

        ValidationData verificationData = billDateEndMonthValidation.validate(resourceName, ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }
}
