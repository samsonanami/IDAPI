package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by MudithaJ on 11/24/2016.
 */
public interface MRZDecodingStrategy {

    MRZDecodeResults decode(String mrz);
}
