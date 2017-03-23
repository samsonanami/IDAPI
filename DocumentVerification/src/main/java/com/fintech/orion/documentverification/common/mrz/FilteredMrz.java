package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.mrz.filter.Filter;
import com.fintech.orion.dto.featurepoint.FeaturePoint;

import java.util.List;

/**
 * Created by TharinduMP on 3/20/2017.
 */
public class FilteredMrz extends CommonMrz implements Mrz {

    private Filter filter;

    public FilteredMrz(String mrzText, List<FeaturePoint> featurePoints, Filter filter) {
        super(mrzText, featurePoints);
        this.filter = filter;
    }

    @Override
    public String getFeature(String featureName) {
        return getFilteredFeatureValue(super.getFeature(featureName));
    }

    private String getFilteredFeatureValue(String unfilteredValue) {
        return filter.filter(unfilteredValue);
    }

}
