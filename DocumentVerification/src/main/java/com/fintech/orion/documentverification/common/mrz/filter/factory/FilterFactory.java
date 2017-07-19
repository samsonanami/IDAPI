package com.fintech.orion.documentverification.common.mrz.filter.factory;

import com.fintech.orion.documentverification.common.mrz.filter.Filter;

/**
 * Created by TharinduMP on 3/21/2017.
 */
@FunctionalInterface
public interface FilterFactory {
    Filter getFilter(String filterName);
}
