package com.fintech.orion.hermes.processor;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.request.RequestProcessDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.mapping.request.GenericRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TharinduMP on 10/11/2016.
 * Main Request Processing Class.
 */
public class RequestProcessor implements RequestProcessorInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    @Autowired
    private GenericRequestMapper genericRequestMapper;

    @Override
    public GenericRequest createGenericRequest(GenericMapMessage genericMapMessage, ProcessDTO processDTO) throws ValidatorException {
        try {
            LOGGER.trace("starting createGenericRequest");
            //validate genericMapMessage
            validatorFactory.getValidator("GenericMapMessageValidator").validate(genericMapMessage);

            //validate RequestProcessDTO
            validatorFactory.getValidator("ProcessDTOValidator").validate(processDTO);

            return genericRequestMapper.mapMessageAndRequestProcessToGenericRequest(processDTO, genericMapMessage);
        } finally {
            LOGGER.trace("createGenericRequest completed");
        }
    }

    @Override
    public List<GenericRequest> createGenericRequestList(GenericMapMessage genericMapMessage, List<ProcessDTO> processList) throws ValidatorException {
        LOGGER.trace("starting createGenericRequestList");
        List<GenericRequest> genericRequests = new ArrayList<>();
        if (processList != null) {
            for (ProcessDTO processDTO : processList) {
                genericRequests.add(createGenericRequest(genericMapMessage, processDTO));
            }
        }
        return genericRequests;
    }
}
