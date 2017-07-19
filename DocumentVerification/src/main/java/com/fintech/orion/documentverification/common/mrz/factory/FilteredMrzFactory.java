package com.fintech.orion.documentverification.common.mrz.factory;

import com.fintech.orion.documentverification.common.mrz.FilteredMrz;
import com.fintech.orion.documentverification.common.mrz.Mrz;
import com.fintech.orion.documentverification.common.mrz.filter.Filter;
import com.fintech.orion.dto.featurepoint.FeaturePoint;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TharinduMP on 3/20/2017.
 */
@Component
public class FilteredMrzFactory implements MrzFactory {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public Mrz getMrz(String mrz, String mrzType, Filter filter) {
        List<FeaturePoint> featurePoints = (List<FeaturePoint>) beanFactory.getBean(mrzType);
        return beanFactory.getBean(FilteredMrz.class, mrz, featurePoints, filter);
    }

}
