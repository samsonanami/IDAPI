package com.fintech.orion.documentverification.common.mrz;

/**
 * This will decode the MRZ
 * Created by MudithaJ on 11/24/2016.
 */
public interface MRZDecodingStrategy {

    MRZDecodeResults decode(String mrz) throws Exception;
}
