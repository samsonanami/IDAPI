package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.Address.AddressCompare;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class AddressValidation  extends ValidationHelper implements CustomValidation {

    @Autowired
    private AddressCompare addressComparator;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        validationData.setId("address");
        OcrFieldData fieldData=getFieldDataById("address",ocrResponse);

        if (fieldData != null && fieldData.getValue() != null && !fieldData.getValue().isEmpty()) {
            validationData = validateData(fieldData.getValue());
        }else{
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not verify document version");
            validationData.setValidationStatus(false);
        }
        return validationData;
    }

    public ValidationData validateData(List<OcrFieldValue> values) throws CustomValidationException
    { try{
        ValidationData  validationData= new  ValidationData();
        int valueCount = 1;
        String dataValue="";
        if(values.size() > 1){
            validationData.setValidationStatus(false);
            validationData.setRemarks("Only one address available");
        }
        for(OcrFieldValue value:values) {

            if(addressComparator.compare(value.getValue(),dataValue).isResult()){
                validationData.setValidationStatus(true);
                validationData.setRemarks("");
            }else{
                validationData.setValidationStatus(false);
                validationData.setRemarks("address not matched");
                break;
            }
            valueCount++;
            dataValue=value.getValue();
        }


        return  validationData;
    }catch (AddressValidatingException e)
    {
        throw new CustomValidationException("Address comparison exception",e);
    }
    }

}
