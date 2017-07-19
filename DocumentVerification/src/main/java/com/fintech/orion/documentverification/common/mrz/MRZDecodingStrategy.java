package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.MRZDecodingException;

/**
 * This will decode the MRZ
 * Created by MudithaJ on 11/24/2016.
 *
 */
public interface MRZDecodingStrategy {

    MRZDecodeResults decode(String mrz) throws MRZDecodingException;
}
