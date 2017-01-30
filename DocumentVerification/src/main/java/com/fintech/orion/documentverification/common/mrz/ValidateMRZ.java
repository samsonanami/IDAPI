package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.MRZValidatingException;

import java.util.Map;

/**
 * This validates the MRZ.
 * Created by MudithaJ on 12/14/2016.
 */
public interface ValidateMRZ {
    ValidateMRZResult validate(String mrz) throws MRZValidatingException;

    String getValidationResultMessage(Map<String, String> validateMap);
}

