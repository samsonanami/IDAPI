package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.dto.messaging.GenericMapMessage;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/11/2016.
 * JobHandlerInterface
 */
public interface JobHandlerInterface {

    Message createGenericMapMessageToMessage(Session session, GenericMapMessage genericMapMessage) throws JobHandlerException;

    GenericMapMessage createMapMessageToGenericMapMessage(MapMessage mapMessage) throws JobHandlerException;
}
