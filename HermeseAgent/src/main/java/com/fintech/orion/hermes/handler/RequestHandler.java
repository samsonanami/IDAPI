package com.fintech.orion.hermes.handler;

import com.fintech.orion.RequestHandlerException;
import com.fintech.orion.common.exceptions.RequestWorkerException;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.hermes.delegate.RequestDelegationInterface;
import com.fintech.orion.hermesagentservices.transmission.request.RequestFactoryServiceInterface;
import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by TharinduMP on 10/14/2016.
 * Class Responsible for delegating Requests to perform request sending.
 */
public class RequestHandler implements RequestHandlerInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandler.class);

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    @Autowired
    private RequestFactoryServiceInterface requestFactoryService;

    @Autowired
    private RequestDelegationInterface requestDelegation;

    @Override
    public void handleRequests(List<GenericRequest> genericRequests) throws RequestHandlerException {
        if (genericRequests != null) {
            for (GenericRequest genericRequest : genericRequests) {
                try {
                    // validate each genericRequest
                    validatorFactory.getValidator(genericRequest).validate(genericRequest);

                    //get each genericRequests a request
                    RequestInterface request = requestFactoryService.getRequest(String.valueOf(genericRequest.getProcessType()));

                    //delegate request to a concurrent worker
                    requestDelegation.delegateRequest(genericRequest,request);

                } catch (ValidatorException e) {
                    LOGGER.error("One of the generic requests failed the validation", e);
                    LOGGER.error("One of the generic requests with IdentificationCode {} failed the validation. Proceeding with other requests without termination", genericRequest.getIdentificationCode());
                } catch (NoSuchBeanDefinitionException e) {
                    LOGGER.error("Matching Bean Definition was not found for the Request. Proceeding with other requests without termination", e);
                } catch (RequestWorkerException e) {
                    LOGGER.error("Worker Initialization failed.Proceeding with other requests without termination",e);
                }
            }
        } else {
            throw new RequestHandlerException("Provided genericRequests was null.");
        }
    }
}
