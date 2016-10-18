package com.fintech.orion.hermes.provider;

import com.fintech.orion.common.exceptions.ProcessProviderException;
import com.fintech.orion.coreservices.ProcessingRequestServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TharinduMP on 10/12/2016.
 * Provides necessary Processes
 */
public class ProcessProvider implements ProcessProviderInterface {

    @Autowired
    private ProcessingRequestServiceInterface processingRequestService;

    @Override
    public List<ProcessDTO> getProcesses(String identificationCode) throws ProcessProviderException {
        try {
            CommonValidations.notBlank(identificationCode,"identificationCode");
            ProcessingRequestDTO processingRequest = processingRequestService.findByIdIdentificationCode(identificationCode);
            return processingRequest.getProcesses();
        } catch (ItemNotFoundException | ValidatorException e) {
            throw new ProcessProviderException(e);
        }
    }

}
