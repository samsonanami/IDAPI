package com.fintech.orion.handlers;

import com.fintech.orion.common.exceptions.job.JobHandlerException;

import javax.jms.Message;

/**
 * MessageHandlerInterface
 */
public interface MessageHandlerInterface {
    Message getMessage(int id, String processingRequestId) throws JobHandlerException;
}
