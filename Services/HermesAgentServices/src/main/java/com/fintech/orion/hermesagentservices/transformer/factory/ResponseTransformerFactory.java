package com.fintech.orion.hermesagentservices.transformer.factory;

import com.fintech.orion.hermesagentservices.transformer.ResponseTransformer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ResponseTransformerFactory {

    @Autowired
    private BeanFactory beanFactory;

    public ResponseTransformer getResponseTransformer(String transformerName){
        return beanFactory.getBean(transformerName, ResponseTransformer.class);
    }
}
