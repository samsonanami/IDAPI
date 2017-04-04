package com.fintech.orion.resource.persistence.workflow.factory;

import com.fintech.orion.resource.persistence.workflow.PersistenceWorkflow;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 3/29/2017.
 */
@Component
public class CommonPersistenceWorkflowFactory implements PersistenceWorkflowFactory {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public PersistenceWorkflow getPersistenceWorkflow(String condition) {
        if("pdf".equalsIgnoreCase(condition)) {
            return (PersistenceWorkflow) beanFactory.getBean("pdfPersistenceWorkflow");
        }
        return (PersistenceWorkflow) beanFactory.getBean("commonPersistenceWorkflow");
    }

}
