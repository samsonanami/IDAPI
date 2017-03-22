package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by TharinduMP on 3/17/2017.
 */
@FunctionalInterface
public interface Mrz {
    String getFeature(String featureName);
}
