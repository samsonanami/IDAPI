package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.dto.request.GenericRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 10/13/2016.
 * JenID API Request Implementation
 */
@Component
@Scope("prototype")
public class JenID extends AbstractRequest implements RequestInterface {

    @Override
    public void process(GenericRequest genericRequest) {
        // initialize the resources and configurations
        super.process(genericRequest);

        //

    }
}
