package com.fintech.orion.hermesagentservices.sanitizer.chain;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.hermese.sanitizer.OcrResultSanitizerConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sasitha on 2/27/17.
 *
 */
public class AddressSanitizerTest {

    private List<String> ocrExtractionFieldList;
    private OcrResultSanitizerConfiguration addressSanitizerConfiguration;
    private AddressSanitizer addressSanitizer;
    private OcrResponse ocrResponse;
    @Before
    public void setup(){
        ocrExtractionFieldList = new ArrayList<>();
        addressSanitizerConfiguration = new OcrResultSanitizerConfiguration();
        addressSanitizer = new AddressSanitizer();
        ocrResponse = new OcrResponse();
        ocrExtractionFieldList.add("drivingLicenseFront##address_line1");
        ocrExtractionFieldList.add("utilityBill##address_line1");
        ocrExtractionFieldList.add("utilityBill##address_line2");
        ocrExtractionFieldList.add("utilityBill##address_line3");

        addressSanitizerConfiguration.setOcrExtractionFields(ocrExtractionFieldList);
        addressSanitizer.setSanitizerConfiguration(addressSanitizerConfiguration);
    }


    @Test
    public void should_sanitize_the_ocr_response(){
        OcrFieldData ocrFieldData = new OcrFieldData();
        OcrFieldValue ocrFieldValue = new OcrFieldValue();
        ocrFieldValue.setValue("42 PYMMES GREEN ROAD. LONDON. N11 1BY");
        ocrFieldValue.setId("drivingLicenseFront##address_line1");
        List<OcrFieldValue> ocrFieldValueList = new ArrayList<>();
        ocrFieldValueList.add(ocrFieldValue);
        ocrFieldData.setValue(ocrFieldValueList);
        List<OcrFieldData> ocrFieldDataList = new ArrayList<>();
        ocrFieldDataList.add(ocrFieldData);
        ocrResponse.setData(ocrFieldDataList);
        addressSanitizer.execute(ocrResponse);

        Assert.assertEquals("42 PYMMES GREEN ROAD, LONDON, N11 1BY", ocrResponse.getData().get(0).getValue().get(0).getValue());
    }
}