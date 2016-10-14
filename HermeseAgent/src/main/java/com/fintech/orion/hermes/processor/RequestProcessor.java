package com.fintech.orion.hermes.processor;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by TharinduMP on 10/11/2016.
 * Main Request Processing Class.
 */
public class RequestProcessor {

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    // get the list

    // validate the list

    // get proper beans for each process

    // prepare genericRequest obj

    // --------


    // pre-license check?

    // post license update

    public void delegateRequests(GenericMapMessage genericMapMessage, List<Process> processes) {

    }


    public GenericRequest createGenericRequest(GenericMapMessage genericMapMessage, Process process) throws ValidatorException {
        //validate genericMapMessage
        validatorFactory.getValidator(genericMapMessage).validate(genericMapMessage);

        GenericRequest genericRequest = new GenericRequest();
        genericRequest.setIdentificationCode(genericMapMessage.getIdentificationCode());
        genericRequest.setClientId(genericMapMessage.getClientId());
        genericRequest.setProcessId(process.getId());
        genericRequest.setProcessType(process.getProcessType().getId());

        return null;

    }

}
