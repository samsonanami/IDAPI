package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.job.JobProducerException;

import java.util.Map;

/**
 * Created by TharinduMP on 10/7/2016.
 */
public interface JobProducerInterface {
    void produceJob(Map<String, Object> taskData) throws JobProducerException;
}
