package com.fintech.orion.resource.persistence.workflow.factory;

import com.fintech.orion.resource.persistence.workflow.PersistenceWorkflow;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface PersistenceWorkflowFactory {
    PersistenceWorkflow getPersistenceWorkflow(String condition);
}
