package com.fintech.orion.resource.persistence.workflow;

import com.fintech.orion.resource.persistence.Persistence;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by TharinduMP on 3/30/2017.
 */
public abstract class AbstractPersistenceWorkflow {

    @Autowired
    protected BeanFactory beanFactory;

    @Autowired
    private String workingDir;

    protected String persist(byte[] bytes, String fileName) throws IOException {
        Persistence persistence = (Persistence) beanFactory.getBean("localStoragePersistence", bytes, workingDir, fileName);
        return persistence.persist();
    }
}
