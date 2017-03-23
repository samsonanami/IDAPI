package com.fintech.orion.documentverification.common.mrz.filter.factory;

import com.fintech.orion.documentverification.common.mrz.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 3/22/2017.
 */
public class CommonFilterFactory implements FilterFactory {

    @Autowired
    private Filter fillerFilter;

    @Override
    public Filter getFilter(String filterName) {
        if("FillerFilter".equals(filterName)) {
            return fillerFilter;
        }
        return null;
    }
}
