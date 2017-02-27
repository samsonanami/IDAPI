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
 */
public class DrivingLicenseMrzSanitizerTest {
    private List<String> ocrExtractionFieldList;
    private OcrResultSanitizerConfiguration addressSanitizerConfiguration;
    private DrivingLicenseMrzSanitizer drivingLicenseMrzSanitizer;
    private OcrResponse ocrResponse;
    @Before
    public void setup(){
        ocrExtractionFieldList = new ArrayList<>();
        addressSanitizerConfiguration = new OcrResultSanitizerConfiguration();
        drivingLicenseMrzSanitizer = new DrivingLicenseMrzSanitizer();
        ocrResponse = new OcrResponse();
        ocrExtractionFieldList.add("drivingLicenseFront##MRZ_line1");

        addressSanitizerConfiguration.setOcrExtractionFields(ocrExtractionFieldList);
        drivingLicenseMrzSanitizer.setSanitizerConfiguration(addressSanitizerConfiguration);
    }


    @Test
    public void should_sanitize_the_ocr_response(){
        OcrFieldData ocrFieldData = new OcrFieldData();
        OcrFieldValue ocrFieldValue = new OcrFieldValue();
        ocrFieldValue.setValue("KU LAR707254PS9RT");
        ocrFieldValue.setId("drivingLicenseFront##MRZ_line1");
        List<OcrFieldValue> ocrFieldValueList = new ArrayList<>();
        ocrFieldValueList.add(ocrFieldValue);
        ocrFieldData.setValue(ocrFieldValueList);
        List<OcrFieldData> ocrFieldDataList = new ArrayList<>();
        ocrFieldDataList.add(ocrFieldData);
        ocrResponse.setData(ocrFieldDataList);
        drivingLicenseMrzSanitizer.execute(ocrResponse);

        Assert.assertEquals("KULAR707254PS9RT", ocrResponse.getData().get(0).getValue().get(0).getValue());
    }
}