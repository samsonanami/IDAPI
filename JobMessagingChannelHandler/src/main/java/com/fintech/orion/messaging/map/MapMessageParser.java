package com.fintech.orion.messaging.map;

import com.fintech.orion.messaging.job.JobCommon;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * Created by TharinduMP on 10/10/2016.
 */
public class MapMessageParser {

    public static MapMessage createMapMessage(JobCommon jobCommon) throws JMSException {
       return jobCommon.getSession().createMapMessage();
    }
}
