package com.fintech.orion.documentverification.common.mrz;

import java.util.HashMap;
import java.util.Map;

import com.fintech.orion.documentverification.common.exception.PassPortMRZValidateException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by MudithaJ on 12/14/2016.
 */
public class ValidatePassPortMRZ implements ValidateMRZ {

    @Autowired
    @Qualifier("passportMRZConfigureList")
    private HashMap<String,MRZItemProperty> mrzItemProperty;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public ValidateMRZResult validate(String mrz) throws PassPortMRZValidateException
    { try {

        String message;
        Map<String, String> validateMap = new HashMap<String, String>();
        ValidateMRZResult validMRZ = new ValidateMRZResult();
        validateMap.put("checkMRZLength", String.valueOf(this.checkMRZLength(mrz)));
          validMRZ.setMRZType("PassPort");
          validMRZ.setItem(mrz);

        if (validateMap.containsValue("false")) {
            validMRZ.setValidationResult("false");
            validMRZ.setMessage(this.getValidationResultMessage(validateMap));
        } else {
            validMRZ.setValidationResult("true");
        }

        return validMRZ;
    }
    catch (NullPointerException e)
    {
      throw new PassPortMRZValidateException("Not well formatted passport MRZ or not well formatted configuration properties",e);

    }
    }
    public MRZItemProperty getConfigValue(String key)
    {
        MRZItemProperty property = mrzItemProperty.get(key);
        return  property;
    }

    public String getValidationResultMessage(Map<String, String> validateMap)
    {
            String message ="";
        for (Map.Entry<String, String> e : validateMap.entrySet()) {
            if(e.getValue() == "false")
            {
                message = message +e.getKey() +"<<";
            }
        }


        return message;
    }
    private boolean checkMRZLength(String mrz)
    {   int lenght = this.getConfigValue("MRZFirstLineLength").getEndIndex() + this.getConfigValue("MRZSecondLineLength").getEndIndex();
        boolean validate = false;
        if(mrz.length() == lenght) {
            validate = true;
        }


        return validate;

    }
    private boolean isPassportMRZ(String mrz)
    {
        if(mrz.charAt(0)=='P') {
            return true;
        }
        else {
            return false;
        }
    }
}
