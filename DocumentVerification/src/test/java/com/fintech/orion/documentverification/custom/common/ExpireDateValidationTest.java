package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sasitha on 12/29/16.
 */
public class ExpireDateValidationTest {
    @InjectMocks
    private ExpireDateValidation expireDateValidation;

    @Mock
    private DateDecoder dateDecoder;

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSex;
    private ResourceName resourceName;
    private SimpleDateFormat dateFormat;
    private String templateCategory;

    @Mock
    private OcrResponseReader responseReader;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataSex = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        templateCategory = null;
        expireDateValidation.setResponseReader(responseReader);
    }

    @Test
    public void should_return_true_if_all_documents_not_expired() throws Exception {

        Mockito.when(dateDecoder.decodeDate("25.12.2017", templateCategory)).thenReturn(dateFormat.parse("12/25/2017"));
        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##expire_date##NPP");
        passportValue.setValue("25.12.2017");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##expire_date##NPP");
        dlFrontValue.setValue("25.12.2017");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("expire_date");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);
        expireDateValidation.setOcrExtractionFieldName("expire_date");
        ocrResponse.setData(fieldDataList);

        ValidationData validationData = expireDateValidation.validate(resourceName, ocrResponse);
        assertTrue(validationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_one_of_the_document_is_expired() throws Exception {
        Mockito.when(dateDecoder.decodeDate("25.12.2010", templateCategory)).thenReturn(dateFormat.parse("12/25/2010"));
        Mockito.when(dateDecoder.decodeDate("25.12.2017", templateCategory)).thenReturn(dateFormat.parse("12/25/2017"));

        OcrFieldValue passportValue = new OcrFieldValue();
        passportValue.setId("passport##expire_date##NPP");
        passportValue.setValue("25.12.2010");

        OcrFieldValue dlFrontValue = new OcrFieldValue();
        dlFrontValue.setId("drivingLicenseFront##expire_date##NPP");
        dlFrontValue.setValue("25.12.2017");

        List<OcrFieldValue> fieldValueList = new ArrayList<>();
        fieldValueList.add(passportValue);
        fieldValueList.add(dlFrontValue);

        ocrFieldDataSex.setId("expire_date");
        ocrFieldDataSex.setValue(fieldValueList);

        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSex);
        expireDateValidation.setOcrExtractionFieldName("expire_date");
        ocrResponse.setData(fieldDataList);

        ValidationData validationData = expireDateValidation.validate(resourceName, ocrResponse);
        assertFalse(validationData.getValidationStatus());
    }

}