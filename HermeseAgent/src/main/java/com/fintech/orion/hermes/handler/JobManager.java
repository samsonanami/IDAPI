package com.fintech.orion.hermes.handler;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermes.processor.MessageProcessorInterface;
import com.fintech.orion.hermes.processor.RequestProcessorInterface;
import com.fintech.orion.hermes.provider.ProcessProviderInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import java.util.List;

/**
 * Created by TharinduMP on 10/11/2016.
 * Class for Managing the Job. this includes processing the message,
 * delegating the message to validations and other job related tasks.
 */
public class JobManager implements JobManagerInterface {

    @Autowired
    private MessageProcessorInterface messageProcessor;

    @Autowired
    private ProcessProviderInterface processProvider;

    @Autowired
    private RequestProcessorInterface requestProcessor;

    @Autowired
    private RequestHandlerInterface requestHandler;

    @Override
    public void delegateJob(Message message) throws Exception {

        //get processed DTO
        GenericMapMessage genericMapMessage = messageProcessor.processMessage(message);

        //get process list allocated for the job
        List<ProcessDTO> processList = processProvider.getProcesses(genericMapMessage.getIdentificationCode());

        //createGenericRequest
        List<GenericRequest> genericRequests = requestProcessor.createGenericRequestList(genericMapMessage,processList);

        //delegate to the request Handler
        requestHandler.handleRequests(genericRequests);
    }
}