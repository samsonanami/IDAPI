package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by MudithaJ on 1/4/2017.
 */
public class NameUtilityBillValidationTest {

    private NameUtilityBillValidation  nameUtilityBillValidation = new  NameUtilityBillValidation();

    private OcrResponse ocrResponse;
    private OcrFieldData ocrFieldDataSurname;
    private OcrFieldData ocrFieldDataGivenname;
    private OcrFieldData ocrFieldDatabillFullnname;
    private ResourceName resourceName;

    @Before
    public void setup()throws Exception{
        ocrFieldDataSurname = new OcrFieldData();
        ocrFieldDataGivenname =   new OcrFieldData();
        ocrFieldDatabillFullnname  = new OcrFieldData();
        ocrResponse = new OcrResponse();
        resourceName = new ResourceName();
    }

    @Test
    public void should_return_true_if_fullname_in_bill_is_similar_to_every_documen_tsurname_and_lastname()throws Exception{
        OcrFieldValue passportSurNameValue = new OcrFieldValue();
        passportSurNameValue.setId("passport##surname");
        passportSurNameValue.setValue("tribiyani");


        OcrFieldValue passportGivenNameValue = new OcrFieldValue();
        passportGivenNameValue.setId("passport##givenname");
        passportGivenNameValue.setValue("joey");

        OcrFieldValue drivingLicenseSurNameValue = new OcrFieldValue();
        drivingLicenseSurNameValue.setId("drivinglicense##surname");
        drivingLicenseSurNameValue.setValue("tribiyani");


        OcrFieldValue drivingLicenseGivenNameValue = new OcrFieldValue();
        drivingLicenseGivenNameValue.setId("drivinglicense##givenname");
        drivingLicenseGivenNameValue.setValue("joey");

        OcrFieldValue billFullNameValue = new OcrFieldValue();
        billFullNameValue.setId("bill##fullname");
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

        nameUtilityBillValidation.setOcrExtractionFieldName("fullname");
        nameUtilityBillValidation.setSurName("surname");
        nameUtilityBillValidation.setGivenName("givenname");
        nameUtilityBillValidation.setUitilityBillfullName("fullname");
        nameUtilityBillValidation.setResourceDrvingLicense("drivinglicense");
        nameUtilityBillValidation.setResoursePassport("passport");
        resourceName.setName("bill");


        ValidationData verificationData = nameUtilityBillValidation.validate(resourceName,ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_false_if_fullname_in_bill_is_different_to_one_document_surname_and_lastname()throws Exception{
        OcrFieldValue passportSurNameValue = new OcrFieldValue();
        passportSurNameValue.setId("passport##surname");
        passportSurNameValue.setValue("tribiyani");


        OcrFieldValue passportGivenNameValue = new OcrFieldValue();
        passportGivenNameValue.setId("passport##givenname");
        passportGivenNameValue.setValue("ross");

        OcrFieldValue drivingLicenseSurNameValue = new OcrFieldValue();
        drivingLicenseSurNameValue.setId("drivinglicense##surname");
        drivingLicenseSurNameValue.setValue("tribiyani");


        OcrFieldValue drivingLicenseGivenNameValue = new OcrFieldValue();
        drivingLicenseGivenNameValue.setId("drivinglicense##givenname");
        drivingLicenseGivenNameValue.setValue("joey");

        OcrFieldValue billFullNameValue = new OcrFieldValue();
        billFullNameValue.setId("bill##fullname");
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

        nameUtilityBillValidation.setOcrExtractionFieldName("fullname");
        nameUtilityBillValidation.setSurName("surname");
        nameUtilityBillValidation.setGivenName("givenname");
        nameUtilityBillValidation.setUitilityBillfullName("fullname");
        nameUtilityBillValidation.setResourceDrvingLicense("drivinglicense");
        nameUtilityBillValidation.setResoursePassport("passport");
        resourceName.setName("bill");


        ValidationData verificationData = nameUtilityBillValidation.validate(resourceName,ocrResponse);
        assertFalse(verificationData.getValidationStatus());
    }

    @Test
    public void should_return_true_if_fullname_in_bill_is_similar_to_one_document_surname_and_lastname_and_only_one_docuemnt_availabe()throws Exception{
        OcrFieldValue passportSurNameValue = new OcrFieldValue();
        passportSurNameValue.setId("passport##surname");
        passportSurNameValue.setValue("tribiyani");


        OcrFieldValue passportGivenNameValue = new OcrFieldValue();
        passportGivenNameValue.setId("passport##givenname");
        passportGivenNameValue.setValue("joey");



        OcrFieldValue billFullNameValue = new OcrFieldValue();
        billFullNameValue.setId("bill##fullname");
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

        nameUtilityBillValidation.setOcrExtractionFieldName("fullname");
        nameUtilityBillValidation.setSurName("surname");
        nameUtilityBillValidation.setGivenName("givenname");
        nameUtilityBillValidation.setUitilityBillfullName("fullname");
        nameUtilityBillValidation.setResourceDrvingLicense("drivinglicense");
        nameUtilityBillValidation.setResoursePassport("passport");
        resourceName.setName("bill");


        ValidationData verificationData = nameUtilityBillValidation.validate(resourceName,ocrResponse);
        assertTrue(verificationData.getValidationStatus());
    }
}
