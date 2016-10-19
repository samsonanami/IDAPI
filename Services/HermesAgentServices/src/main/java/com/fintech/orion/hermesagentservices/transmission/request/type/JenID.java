package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.RequestException;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TharinduMP on 10/13/2016.
 * JenID API Request Implementation
 */
@Component
@Scope("prototype")
public class JenID extends AbstractRequest implements RequestInterface {

    @Override
    public void process(GenericRequest genericRequest) throws RequestException {

        // initialize the resources and configurations
        super.process(genericRequest);

        // validate if configuration map has all the necessary values

        // resources to files creator : how resource deletion will work with this?

        // create jen id body

        // make jen id call

        // status update : call response handler

        // license update : will be done by the response handler


    }
}
