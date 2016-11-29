package com.fintech.orion.api.service.messenging;

import com.fintech.orion.common.exceptions.job.JobHandlerException;

import javax.jms.Message;

/**
 * MessageHandlerInterface
 */
public interface MessageHandlerInterface {
    Message getMessage(int id, String processingRequestId) throws JobHandlerException;
}
