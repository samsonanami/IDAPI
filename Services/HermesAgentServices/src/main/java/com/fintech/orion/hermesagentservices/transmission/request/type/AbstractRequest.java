package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.request.RequestException;
import com.fintech.orion.coreservices.ProcessConfigServiceInterface;
import com.fintech.orion.coreservices.ProcessServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.resource.ResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by TharinduMP on 10/13/2016.
 * AbstractRequest responsible for getting the initial resources and configurations
 */
public abstract class AbstractRequest {


    @Autowired
    private ProcessServiceInterface processService;

    @Autowired
    private ProcessConfigServiceInterface processConfigService;

    protected List<ResourceDTO> resourceList;
    protected Map<String, String> processConfigurationMap;
    protected ProcessDTO processDTO;

    public void initialize(GenericRequest genericRequest) throws RequestException {
        try {
            this.resourceList = this.getProcessResources(genericRequest);
            this.processConfigurationMap = this.getProcessConfigurations(genericRequest);
            this.processDTO = this.getProcessDetails(genericRequest);
        } catch (ItemNotFoundException e) {
            throw new RequestException(e);
        }
    }

    private List<ResourceDTO> getProcessResources(GenericRequest genericRequest) throws ItemNotFoundException {
        return processService.resourceDTOsForProcess(genericRequest.getProcessId());
    }

    private Map<String, String> getProcessConfigurations(GenericRequest genericRequest) throws ItemNotFoundException {
        List<ProcessConfigDTO> processConfigurationList = processConfigService.findById(genericRequest.getProcessType());
        return processConfigurationList.stream().collect(Collectors.toMap(ProcessConfigDTO::getKey,ProcessConfigDTO::getValue));
    }

    private ProcessDTO getProcessDetails(GenericRequest genericRequest) throws ItemNotFoundException {
        return processService.findByIdentificationCode(genericRequest.getIdentificationCode());
    }

    public void process(GenericRequest genericRequest) throws RequestException {
        initialize(genericRequest);
    }
}
