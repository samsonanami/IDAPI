package com.fintech.orion.resource.validation.factory;

import com.fintech.orion.resource.validation.CommonValidation;
import com.fintech.orion.resource.validation.Validation;
import com.fintech.orion.resource.support.ResourceSupport;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 3/29/2017.
 */
@Component
public class CommonValidationFactory implements ValidationFactory {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceSupport imageResourceSupport;

    @Autowired
    private ResourceSupport videoResourceSupport;

    @Autowired
    private ResourceSupport documentResourceSupport;


    @Override
    public Validation getValidation(String contentType) {
        if("image".equalsIgnoreCase(contentType)) {
            return beanFactory.getBean(CommonValidation.class, imageResourceSupport);
        }
        if("video".equalsIgnoreCase(contentType)) {
            return beanFactory.getBean(CommonValidation.class, videoResourceSupport);
        }
        if("file".equalsIgnoreCase(contentType)) {
            return beanFactory.getBean(CommonValidation.class, documentResourceSupport);
        }
        return null;
    }
}
