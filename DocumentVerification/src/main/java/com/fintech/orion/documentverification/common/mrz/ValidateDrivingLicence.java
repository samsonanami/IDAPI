package com.fintech.orion.documentverification.common.mrz;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class ValidateDrivingLicence  implements ValidateMRZ{

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ValidateDrivingLicence.class);
    @Override
    public boolean validate(String mrz )
    { try {
        boolean validMRZ;
        Map<String, String> validateMap = new HashMap<String, String>();

        validateMap.put("checkMRZLength", String.valueOf(this.checkMRZLength(mrz)));


        if (validateMap.containsValue("false")) {
            validMRZ = false;
        } else {
            validMRZ = true;
        }

        return validMRZ;
    }
    catch (NullPointerException e)
    {
        LOGGER.error("MRZ Validation fail for DL-"+mrz);
        return false;
    }
    }

    private boolean checkMRZLength(String mrz)
    {
        boolean validate = false;
        if(mrz.length() == 16) {
            validate = true;
        }

        return validate;

    }

}
