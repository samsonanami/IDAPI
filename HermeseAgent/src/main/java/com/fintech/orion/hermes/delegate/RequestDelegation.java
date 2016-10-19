package com.fintech.orion.hermes.delegate;

import com.fintech.orion.common.exceptions.RequestWorkerException;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermes.run.RequestWorker;
import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by TharinduMP on 10/17/2016.
 * Request Delegation to the spring Managed Executor Service
 */
public class RequestDelegation implements RequestDelegationInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestDelegation.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void delegateRequest(GenericRequest genericRequest, RequestInterface request) throws RequestWorkerException {
        // bean initialization using context to create a new instance every time
        LOGGER.debug("getting a new worker instance");
        RequestWorker worker = (RequestWorker) context.getBean("requestWorker");
        // initialization of the worker
        worker.init(request,genericRequest);
        // add to the task executor to manage the concurrent process
        LOGGER.trace("submitting worker to task executor");
        taskExecutor.submit(worker);
        LOGGER.trace("submitted worker to task executor");
    }
}