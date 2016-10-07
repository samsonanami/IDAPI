package com.fintech.orion.messaging.job;

import javax.jms.JMSException;
import javax.jms.MessageListener;

/**
 * Created by TharinduMP on 10/7/2016.
 */
public interface JobConsumerInterface {
    void setJobConsumer(MessageListener messageListener) throws JMSException;
}
