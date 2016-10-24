package com.fintech.orion.handlers;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.common.exceptions.job.JobProducerException;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

/**
 * OrionJobHandlerInterface
 */
public interface OrionJobHandlerInterface {

    void sendProcess(String accessToken, String processingRequestId) throws ItemNotFoundException, JobHandlerException, JobProducerException;

}
