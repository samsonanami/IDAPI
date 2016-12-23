package com.fintech.orion.documentverification.common.mrz;

import java.util.Map;

/**
 * Created by MudithaJ on 12/14/2016.
 */
public interface ValidateMRZ {
    ValidateMRZResult validate(String mrz) throws Exception;
    String getValidationResultMessage(Map<String, String> validateMap);
}

