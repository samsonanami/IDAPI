package com.fintech.orion.documentverification.common.mrz.factory;

import com.fintech.orion.documentverification.common.mrz.Mrz;
import com.fintech.orion.documentverification.common.mrz.filter.Filter;

/**
 * Created by TharinduMP on 3/20/2017.
 */
@FunctionalInterface
public interface MrzFactory {
    Mrz getMrz(String mrz, String mrzType, Filter filter);
}
