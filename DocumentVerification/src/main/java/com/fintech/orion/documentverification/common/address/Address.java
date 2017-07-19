package com.fintech.orion.documentverification.common.address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;

/**
 * Address compare operation
 * Created by MudithaJ on 12/23/2016.
 */
public interface Address {
    AddressCompareResult compare(String addressOne, String addressTwo) throws AddressValidatingException;
}
