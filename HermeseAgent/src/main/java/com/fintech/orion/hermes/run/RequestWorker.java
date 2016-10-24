package com.fintech.orion.hermes.run;

import com.fintech.orion.common.exceptions.request.RequestException;
import com.fintech.orion.common.exceptions.RequestWorkerException;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Created by TharinduMP on 10/17/2016.
 * Main Callable Worker.
 */
@Component
@Scope("prototype")
public class RequestWorker implements Callable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestWorker.class);

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    private RequestInterface request;
    private GenericRequest genericRequest;
    private boolean initialized;

    public void init(RequestInterface request, GenericRequest genericRequest) throws RequestWorkerException {
        try {
            LOGGER.info("Initializing a worker with request IdentificationCode {}.", genericRequest.getIdentificationCode());

            //validations
            CommonValidations.notNull(request,"request object");
            validatorFactory.getValidator("GenericRequestValidator").validate(validatorFactory);

            this.request = request;
            this.genericRequest = genericRequest;
            this.initialized = true;
            LOGGER.trace("Initializing a worker with request IdentificationCode {} is completed.", genericRequest.getIdentificationCode());
        } catch (ValidatorException e) {
            throw new RequestWorkerException(e);
        }
    }

    @Override
    public Object call() throws Exception {
        if (initialized) {
            LOGGER.debug("worker processing started.");
            try {
                request.process(genericRequest);
            } catch (RequestException e) {
                LOGGER.error("Request Exception", e);
                throw new RequestWorkerException(e);
            }
            return null;
        } else {
            LOGGER.error("Worker is not Initialized. Make sure the worker is initialized before calling.");
            throw new RequestWorkerException("Worker is not Initialized. Make sure the worker is initialized before calling.");
        }
    }
}
