package com.fintech.orion.hermes.processor;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorException;

import java.util.List;

/**
 * Created by TharinduMP on 10/18/2016.
 * RequestProcessorInterface
 */
public interface RequestProcessorInterface {
    GenericRequest createGenericRequest(GenericMapMessage genericMapMessage, ProcessDTO process) throws ValidatorException;

    List<GenericRequest> createGenericRequestList(GenericMapMessage genericMapMessage, List<ProcessDTO> processList) throws ValidatorException;
}
