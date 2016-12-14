package com.fintech.orion.documentverification.common.mrz;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

/**
 * Created by MudithaJ on 12/14/2016.
 */
public class ValidatePassPortMRZ implements ValidateMRZ {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ValidatePassPortMRZ.class);
    public boolean validate(String mrz )
    { try {
        boolean validMRZ;
        Map<String, String> validateMap = new HashMap<String, String>();

        validateMap.put("checkMRZLength", String.valueOf(this.checkMRZLength(mrz)));
        validateMap.put("isPassportMRZ", String.valueOf(this.isPassportMRZ(mrz)));


        if (validateMap.containsValue("false")) {
            validMRZ = false;
        } else {
            validMRZ = true;
        }

        return validMRZ;
    }
    catch (NullPointerException e)
    {
        LOGGER.error("MRZ Validation fail for -"+mrz);
        return false;
    }
    }

    private boolean checkMRZLength(String mrz)
    {
        boolean validate = false;
        if(mrz.length() == 88) {
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
