package com.fintech.orion.handlers;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.common.exceptions.job.JobProducerException;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.validation.ClientValidator;
import com.fintech.orion.messaging.job.JobProducerInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;

/**
 * OrionJobHandler
 */
public class OrionJobHandler implements OrionJobHandlerInterface {

    @Autowired
    private JobProducerInterface jobProducerInterface;

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private MessageHandlerInterface messageHandlerInterface;


    @Override
    public void sendProcess(String accessToken, String processingRequestId) throws ItemNotFoundException, JobHandlerException, JobProducerException {
        ClientDTO clientDTO = clientValidator.checkClientValidity(accessToken);

        Message message = messageHandlerInterface.getMessage(clientDTO.getId(), processingRequestId);

        jobProducerInterface.sendJob(message);
    }
}
