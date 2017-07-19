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
public class DateSanitizerTest {
    private List<String> ocrExtractionFieldList;
    private OcrResultSanitizerConfiguration addressSanitizerConfiguration;
    private DateSanitizer dateSanitizer;
    private OcrResponse ocrResponse;
    @Before
    public void setup(){
        ocrExtractionFieldList = new ArrayList<>();
        addressSanitizerConfiguration = new OcrResultSanitizerConfiguration();
        dateSanitizer = new DateSanitizer();
        ocrResponse = new OcrResponse();
        ocrExtractionFieldList.add("passport##date_of_issue");

        addressSanitizerConfiguration.setOcrExtractionFields(ocrExtractionFieldList);
        dateSanitizer.setSanitizerConfiguration(addressSanitizerConfiguration);
    }


    @Test
    public void should_sanitize_the_ocr_response(){
        OcrFieldData ocrFieldData = new OcrFieldData();
        OcrFieldValue ocrFieldValue = new OcrFieldValue();
        ocrFieldValue.setValue(". 12.10.2014");
        ocrFieldValue.setId("passport##date_of_issue");
        List<OcrFieldValue> ocrFieldValueList = new ArrayList<>();
        ocrFieldValueList.add(ocrFieldValue);
        ocrFieldData.setValue(ocrFieldValueList);
        List<OcrFieldData> ocrFieldDataList = new ArrayList<>();
        ocrFieldDataList.add(ocrFieldData);
        ocrResponse.setData(ocrFieldDataList);
        dateSanitizer.execute(ocrResponse);

        Assert.assertEquals("12.10.2014", ocrResponse.getData().get(0).getValue().get(0).getValue());
    }
}