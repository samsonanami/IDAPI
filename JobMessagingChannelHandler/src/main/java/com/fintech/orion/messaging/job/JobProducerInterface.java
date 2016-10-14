package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.job.JobProducerException;

import javax.jms.Message;

/**
 * Created by TharinduMP on 10/7/2016.
 */
@FunctionalInterface
public interface JobProducerInterface {
    void sendJob(Message message) throws JobProducerException;
}
