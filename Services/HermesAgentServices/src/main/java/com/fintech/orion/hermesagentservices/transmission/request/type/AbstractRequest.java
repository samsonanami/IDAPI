package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.RequestException;
import com.fintech.orion.coreservices.ProcessConfigServiceInterface;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
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
    private ResourceServiceInterface resourceService;

    @Autowired
    private ProcessConfigServiceInterface processConfigService;

    protected List<ResourceDTO> resourceList;
    protected Map<String, String> processConfigurationMap;

    public void initialize(GenericRequest genericRequest) throws RequestException {
        try {
            this.resourceList = this.getProcessResources(genericRequest);
            this.processConfigurationMap = this.getProcessConfigurations(genericRequest);
        } catch (ItemNotFoundException e) {
            throw new RequestException(e);
        }
    }

    private List<ResourceDTO> getProcessResources(GenericRequest genericRequest) {
        return null;
    }

    private Map<String, String> getProcessConfigurations(GenericRequest genericRequest) throws ItemNotFoundException {
        List<ProcessConfigDTO> processConfigurationList = processConfigService.findById(genericRequest.getProcessType());
        return processConfigurationList.stream().collect(Collectors.toMap(ProcessConfigDTO::getKey,ProcessConfigDTO::getValue));
    }

    public void process(GenericRequest genericRequest) throws RequestException {
        initialize(genericRequest);
    }
}
