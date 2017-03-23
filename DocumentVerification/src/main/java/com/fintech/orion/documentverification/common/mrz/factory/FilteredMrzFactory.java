package com.fintech.orion.documentverification.common.mrz.factory;

import com.fintech.orion.documentverification.common.mrz.FilteredMrz;
import com.fintech.orion.documentverification.common.mrz.Mrz;
import com.fintech.orion.documentverification.common.mrz.filter.Filter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 3/20/2017.
 */
@Component
public class FilteredMrzFactory implements MrzFactory {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public Mrz getMrz(String mrz, String mrzType, Filter filter) {
        return beanFactory.getBean(FilteredMrz.class, mrz, beanFactory.getBean(mrzType), filter);
    }

}
