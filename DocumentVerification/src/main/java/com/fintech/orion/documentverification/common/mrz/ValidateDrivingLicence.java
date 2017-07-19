package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.DirivingLicenseMRZValidatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will validate the driving license MRZ.
 * Created by MudithaJ on 12/16/2016.
 */
public class ValidateDrivingLicence implements ValidateMRZ {
    private static final String FALSE = "false";

    @Resource(name = "drivingLicenseMRZConfigureList")
    private HashMap<String, MRZItemProperty> mrzItemProperty;

    @Override
    public ValidateMRZResult validate(String mrz) throws DirivingLicenseMRZValidatingException {
        try {
            ValidateMRZResult validMRZ = new ValidateMRZResult();
            validMRZ.setMRZType("DrivingLicense");
            validMRZ.setItem(mrz);

            Map<String, String> validateMap = new HashMap<String, String>();

            validateMap.put("checkMRZLength", String.valueOf(this.checkMRZLength(mrz)));


            if (validateMap.containsValue(FALSE)) {
                validMRZ.setValidationResult(FALSE);
                validMRZ.setMessage(this.getValidationResultMessage(validateMap));
            } else {
                validMRZ.setValidationResult("true");
            }

            return validMRZ;
        } catch (NullPointerException e) {

            throw new DirivingLicenseMRZValidatingException("Not well formatted Driving license MRZ or not well set configuration properties", e);
        }
    }

    @Override
    public String getValidationResultMessage(Map<String, String> validateMap) {
        String message = "";
        for (Map.Entry<String, String> e : validateMap.entrySet()) {
            if (e.getValue() == "false") {
                message = message + e.getKey() + "<<";
            }
        }

        return message;
    }

    private boolean checkMRZLength(String mrz) {
        boolean validate = false;
        int length = this.getConfigValue("MZRLength").getEndIndex();
        if (mrz.length() == length) {
            validate = true;
        }

        return validate;

    }

    public MRZItemProperty getConfigValue(String key) {
        return mrzItemProperty.get(key);
    }

}
