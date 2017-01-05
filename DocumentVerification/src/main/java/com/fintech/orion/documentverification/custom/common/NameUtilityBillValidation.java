package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.DateComparatorException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.*;

/**
 * Created by MudithaJ on 1/4/2017.
 */
public class NameUtilityBillValidation  extends ValidationHelper implements CustomValidation {

    private String  utilityBillfullName;
    private String  surName;
    private String  givenName;
    private String resoursePassport;
    private String  resourceDrvingLicense;




    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (utilityBillfullName.equals(null)){
            throw new CustomValidationException("Utility bill full name  extraction field name parameters missing");
        }

        if (surName.equals(null) || givenName.equals(null)){
            throw new CustomValidationException("SurName / Given Names extraction field name parameters missing");
        }
        ValidationData validationData = new ValidationData();
        List<String> fullNameList= new ArrayList<String>();

        OcrFieldData fieldDataSurname = getFieldDataById(surName, ocrResponse);
        OcrFieldData fieldDataGivenname = getFieldDataById(givenName, ocrResponse);
        OcrFieldData fieldDataUtilityBillFullname = getFieldDataById(utilityBillfullName,ocrResponse);

        fullNameList.add(getFullName(resoursePassport, fieldDataSurname, fieldDataGivenname));
        fullNameList.add(getFullName(resourceDrvingLicense, fieldDataSurname, fieldDataGivenname));
        String fullName = getFieldValueById(resourceName.getName() + "##" + utilityBillfullName, fieldDataUtilityBillFullname).getValue();

        validationData = validateInput(fieldDataUtilityBillFullname);

        if (validationData.getValidationStatus()){

            validationData = validateBillName(fullNameList,fullName);

        }
        else{
            validationData.setRemarks(getSuccessRemarksMessage());
        }

          return  validationData ;
    }

    private ValidationData validateBillName(List<String> stringList,String billFullName)  {
       ValidationData validationData = new ValidationData();
        stringList.removeAll(Collections.singleton(null));
        for(String fullName:stringList)
        {
            if(fullName.equalsIgnoreCase(billFullName))
            {
                validationData.setValidationStatus(true);
                validationData.setValue(billFullName);

            }else
            {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(billFullName));
                validationData.setValidationStatus(false);
                break;
            }
        }
        return validationData;
    }


    private String getFullName(String resourceName,OcrFieldData fieldDataSurname,OcrFieldData fieldDataGivename)
    {
        String fullName;

        OcrFieldValue  valueSurName= getFieldValueById(resourceName+"##"+surName,fieldDataSurname);
        OcrFieldValue  valueGivenName= getFieldValueById(resourceName+"##"+givenName,fieldDataGivename);
        if(valueSurName.getValue() == null || valueGivenName.getValue() == null) {
            fullName = null;
        }else
        {
           fullName = valueSurName.getValue()+" "+valueGivenName.getValue();
        }
        return fullName;
    }
    public void setUitilityBillfullName(String uitilityBillfullName) {
        this.utilityBillfullName = uitilityBillfullName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setUtilityBillfullName(String utilityBillfullName) {
        this.utilityBillfullName = utilityBillfullName;
    }

    public void setResoursePassport(String resoursePassport) {
        this.resoursePassport = resoursePassport;
    }

    public void setResourceDrvingLicense(String resourceDrvingLicense) {
        this.resourceDrvingLicense = resourceDrvingLicense;
    }

}
