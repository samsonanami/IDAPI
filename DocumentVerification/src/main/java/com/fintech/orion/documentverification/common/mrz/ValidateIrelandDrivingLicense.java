package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.DirivingLicenseMRZValidatingException;
import com.fintech.orion.documentverification.common.exception.MRZValidatingException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TharinduMP on 3/22/2017.
 * Used the same structure as the other implementations of the interface.
 * This class structure and the interface needs a refactor.
 */
public class ValidateIrelandDrivingLicense implements ValidateMRZ {

    private static final String FALSE = "false";
    private static final int MRZ_LENGTH = 30;

    @Override
    public ValidateMRZResult validate(String mrz) throws MRZValidatingException {

        ValidateMRZResult validMRZ = new ValidateMRZResult();
        try {
            validMRZ.setMRZType("IrelandDrivingLicense");
            validMRZ.setItem(mrz);

            Map<String, String> validateMap = new HashMap<>();
            validateMap.put("checkMRZLength", String.valueOf(this.checkMRZLength(mrz)));

            if (validateMap.containsValue(FALSE)) {
                validMRZ.setValidationResult(FALSE);
                validMRZ.setMessage(this.getValidationResultMessage(validateMap));
            } else {
                validMRZ.setValidationResult("true");
            }

        } catch (NullPointerException e) {
            throw new DirivingLicenseMRZValidatingException("Not well formatted Driving license MRZ or not well set configuration properties", e);
        }

        return validMRZ;
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
        if (mrz.length() == MRZ_LENGTH) {
            validate = true;
        }
        return validate;

    }
}
