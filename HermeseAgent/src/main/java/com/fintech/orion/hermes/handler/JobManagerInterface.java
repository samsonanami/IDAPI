package com.fintech.orion.hermes.handler;

import com.fintech.orion.common.exceptions.JobManagerException;

import javax.jms.Message;

/**
 * Created by TharinduMP on 10/13/2016.
 * JobManagerInterface
 */
@FunctionalInterface
public interface JobManagerInterface {
    void delegateJob(Message message) throws JobManagerException;
}
