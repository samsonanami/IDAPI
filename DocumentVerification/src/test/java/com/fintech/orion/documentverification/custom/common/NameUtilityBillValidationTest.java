package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
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
 * Created by MudithaJ on 1/4/2017.
 */
public class NameUtilityBillValidationTest {
    @InjectMocks
    private NameUtilityBillValidation nameUtilityBillValidation;


    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSurname;
    private OcrFieldData ocrFieldDataGivenname;
    private OcrFieldData ocrFieldDatabillFullnname;
    private ResourceName resourceName;
    private List<String> delimiterList;

    @Spy
    private List<String> preProcessNonePreProcessDelimiterList = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        ocrFieldDataSurname = new OcrFieldData();
        ocrFieldDataGivenname = new OcrFieldData();
        ocrFieldDatabillFullnname = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
        delimiterList = new ArrayList<>();
        delimiterList.add("NPP");
        delimiterList.add("PP");

    }

    @Test
    public void should_return_true_if_fullname_in_bill_is_similar_to_every_documen_tsurname_and_lastname() throws Exception {
        preProcessNonePreProcessDelimiterList.add("NPP");
        preProcessNonePreProcessDelimiterList.add("PP");

        Mockito.verify(preProcessNonePreProcessDelimiterList).add("NPP");
        Mockito.verify(preProcessNonePreProcessDelimiterList).add("PP");

        OcrFieldValue passportSurNameValue = new OcrFieldValue();
        passportSurNameValue.setId("passport##surname##NPP");
        passportSurNameValue.setValue("tribiyani");


        OcrFieldValue passportGivenNameValue = new OcrFieldValue();
        passportGivenNameValue.setId("passport##givenname##NPP");
        passportGivenNameValue.setValue("joey");

        OcrFieldValue drivingLicenseSurNameValue = new OcrFieldValue();
        drivingLicenseSurNameValue.setId("drivinglicense##surname##NPP");
        drivingLicenseSurNameValue.setValue("tribiyani");


        OcrFieldValue drivingLicenseGivenNameValue = new OcrFieldValue();
        drivingLicenseGivenNameValue.setId("drivinglicense##givenname##NPP");
        drivingLicenseGivenNameValue.setValue("joey");

        OcrFieldValue billFullNameValue = new OcrFieldValue();
        billFullNameValue.setId("utilityBill##fullname##NPP");
        billFullNameValue.setValue("tribiyani joey");


        List<OcrFieldValue> surNamefieldValueList = new ArrayList<>();
        surNamefieldValueList.add(passportSurNameValue);
        surNamefieldValueList.add(drivingLicenseSurNameValue);

        ocrFieldDataSurname.setId("surname");
        ocrFieldDataSurname.setValue(surNamefieldValueList);

        List<OcrFieldValue> givenNameValueList = new ArrayList<>();
        givenNameValueList.add(passportGivenNameValue);
        givenNameValueList.add(drivingLicenseGivenNameValue);

        ocrFieldDataGivenname.setId("givenname");
        ocrFieldDataGivenname.setValue(givenNameValueList);

        List<OcrFieldValue> fullNameValueList = new ArrayList<>();
        fullNameValueList.add(billFullNameValue);

        ocrFieldDatabillFullnname.setId("fullname");
        ocrFieldDatabillFullnname.setValue(fullNameValueList);


        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSurname);
        fieldDataList.add(ocrFieldDataGivenname);
        fieldDataList.add(ocrFieldDatabillFullnname);

        ocrResponse.setData(fieldDataList);
        List<String> resourceList = new ArrayList<>();
        resourceList.add("passport");
        resourceList.add("drivinglicense");
        nameUtilityBillValidation.setOcrExtractionFieldName("fullname");
        nameUtilityBillValidation.setSurnameOcrExtractionFieldName("surname");
        nameUtilityBillValidation.setGivenNamesOcrExtractionFieldName("givenname");
        nameUtilityBillValidation.setUtilityBillNameOcrExtractionField("fullname");
        nameUtilityBillValidation.setResourceNameListToCheck(resourceList);
        resourceName.setName("utilityBill");


        ValidationData verificationData = nameUtilityBillValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_fullname_in_bill_is_different_to_one_document_surname_and_lastname() throws Exception {

        preProcessNonePreProcessDelimiterList.add("NPP");
        preProcessNonePreProcessDelimiterList.add("PP");

        Mockito.verify(preProcessNonePreProcessDelimiterList).add("NPP");
        Mockito.verify(preProcessNonePreProcessDelimiterList).add("PP");


        OcrFieldValue passportSurNameValue = new OcrFieldValue();
        passportSurNameValue.setId("passport##surname##PP");
        passportSurNameValue.setValue("tribiyani");


        OcrFieldValue passportGivenNameValue = new OcrFieldValue();
        passportGivenNameValue.setId("passport##givenname##PP");
        passportGivenNameValue.setValue("ross");

        OcrFieldValue drivingLicenseSurNameValue = new OcrFieldValue();
        drivingLicenseSurNameValue.setId("drivinglicense##surname##PP");
        drivingLicenseSurNameValue.setValue("tribiyani");


        OcrFieldValue drivingLicenseGivenNameValue = new OcrFieldValue();
        drivingLicenseGivenNameValue.setId("drivinglicense##givenname##PP");
        drivingLicenseGivenNameValue.setValue("joey");

        OcrFieldValue billFullNameValue = new OcrFieldValue();
        billFullNameValue.setId("utilityBill##fullname##PP");
        billFullNameValue.setValue("tribiyani joey");


        List<OcrFieldValue> surNamefieldValueList = new ArrayList<>();
        surNamefieldValueList.add(passportSurNameValue);
        surNamefieldValueList.add(drivingLicenseSurNameValue);

        ocrFieldDataSurname.setId("surname");
        ocrFieldDataSurname.setValue(surNamefieldValueList);

        List<OcrFieldValue> givenNameValueList = new ArrayList<>();
        givenNameValueList.add(passportGivenNameValue);
        givenNameValueList.add(drivingLicenseGivenNameValue);

        ocrFieldDataGivenname.setId("givenname");
        ocrFieldDataGivenname.setValue(givenNameValueList);

        List<OcrFieldValue> fullNameValueList = new ArrayList<>();
        fullNameValueList.add(billFullNameValue);

        ocrFieldDatabillFullnname.setId("fullname");
        ocrFieldDatabillFullnname.setValue(fullNameValueList);


        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSurname);
        fieldDataList.add(ocrFieldDataGivenname);
        fieldDataList.add(ocrFieldDatabillFullnname);

        ocrResponse.setData(fieldDataList);
        List<String> resourceList = new ArrayList<>();
        resourceList.add("passport");
        resourceList.add("drivinglicense");
        nameUtilityBillValidation.setOcrExtractionFieldName("fullname");
        nameUtilityBillValidation.setSurnameOcrExtractionFieldName("surname");
        nameUtilityBillValidation.setGivenNamesOcrExtractionFieldName("givenname");
        nameUtilityBillValidation.setUtilityBillNameOcrExtractionField("fullname");
        nameUtilityBillValidation.setResourceNameListToCheck(resourceList);
        resourceName.setName("utilityBill");


        ValidationData verificationData = nameUtilityBillValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_fullname_in_bill_is_similar_to_one_document_surname_and_lastname_and_only_one_docuemnt_availabe() throws Exception {

        preProcessNonePreProcessDelimiterList.add("NPP");
        preProcessNonePreProcessDelimiterList.add("PP");

        Mockito.verify(preProcessNonePreProcessDelimiterList).add("NPP");
        Mockito.verify(preProcessNonePreProcessDelimiterList).add("PP");

        OcrFieldValue passportSurNameValue = new OcrFieldValue();
        passportSurNameValue.setId("passport##surname##PP");
        passportSurNameValue.setValue("tribiyani");


        OcrFieldValue passportGivenNameValue = new OcrFieldValue();
        passportGivenNameValue.setId("passport##givenname##PP");
        passportGivenNameValue.setValue("joey");


        OcrFieldValue billFullNameValue = new OcrFieldValue();
        billFullNameValue.setId("utilityBill##fullname##PP");
        billFullNameValue.setValue("tribiyani joey");


        List<OcrFieldValue> surNamefieldValueList = new ArrayList<>();
        surNamefieldValueList.add(passportSurNameValue);


        ocrFieldDataSurname.setId("surname");
        ocrFieldDataSurname.setValue(surNamefieldValueList);

        List<OcrFieldValue> givenNameValueList = new ArrayList<>();
        givenNameValueList.add(passportGivenNameValue);


        ocrFieldDataGivenname.setId("givenname");
        ocrFieldDataGivenname.setValue(givenNameValueList);

        List<OcrFieldValue> fullNameValueList = new ArrayList<>();
        fullNameValueList.add(billFullNameValue);

        ocrFieldDatabillFullnname.setId("fullname");
        ocrFieldDatabillFullnname.setValue(fullNameValueList);


        List<OcrFieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(ocrFieldDataSurname);
        fieldDataList.add(ocrFieldDataGivenname);
        fieldDataList.add(ocrFieldDatabillFullnname);

        ocrResponse.setData(fieldDataList);
        List<String> resourceList = new ArrayList<>();
        resourceList.add("passport");
        resourceList.add("drivinglicense");
        nameUtilityBillValidation.setOcrExtractionFieldName("fullname");
        nameUtilityBillValidation.setSurnameOcrExtractionFieldName("surname");
        nameUtilityBillValidation.setGivenNamesOcrExtractionFieldName("givenname");
        nameUtilityBillValidation.setUtilityBillNameOcrExtractionField("fullname");
        nameUtilityBillValidation.setResourceNameListToCheck(resourceList);
        resourceName.setName("utilityBill");


        ValidationData verificationData = nameUtilityBillValidation.validate(resourceName, ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }
}
