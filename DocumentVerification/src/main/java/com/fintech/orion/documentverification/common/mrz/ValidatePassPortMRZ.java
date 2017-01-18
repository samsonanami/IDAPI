package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.PassPortMRZValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MudithaJ on 12/14/2016.
 */
public class ValidatePassPortMRZ implements ValidateMRZ {
    private static final String FALSE = "false";
    @Autowired
    @Qualifier("passportMRZConfigureList")
    private HashMap<String, MRZItemProperty> mrzItemProperty;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public ValidateMRZResult validate(String mrz) throws PassPortMRZValidateException {
        try {
            Map<String, String> validateMap = new HashMap<String, String>();
            ValidateMRZResult validMRZ = new ValidateMRZResult();
            validateMap.put("checkMRZLength", String.valueOf(this.checkMRZLength(mrz)));
            validMRZ.setMRZType("PassPort");
            validMRZ.setItem(mrz);

            if (validateMap.containsValue(FALSE)) {
                validMRZ.setValidationResult(FALSE);
                validMRZ.setMessage(this.getValidationResultMessage(validateMap));
            } else {
                validMRZ.setValidationResult("true");
            }

            return validMRZ;
        } catch (NullPointerException e) {
            throw new PassPortMRZValidateException("Not well formatted passport MRZ or not well formatted configuration properties", e);

        }
    }

    public MRZItemProperty getConfigValue(String key) {
        return mrzItemProperty.get(key);
    }

    public String getValidationResultMessage(Map<String, String> validateMap) {
        String validationResultMessage = "";
        for (Map.Entry<String, String> e : validateMap.entrySet()) {
            if (FALSE.equalsIgnoreCase(e.getValue())) {
                validationResultMessage = validationResultMessage + e.getKey() + "<<";
            }
        }

        return message;
    }

    private boolean checkMRZLength(String mrz) {
        int lenght = this.getConfigValue("MRZFirstLineLength").getEndIndex() + this.getConfigValue("MRZSecondLineLength").getEndIndex();
        boolean validate = false;
        if (mrz.length() == lenght) {
            validate = true;
        }


        return validate;

    }
}
