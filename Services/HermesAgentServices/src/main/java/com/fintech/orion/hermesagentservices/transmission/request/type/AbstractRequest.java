package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.coreservices.ProcessConfigServiceInterface;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dto.request.GenericRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by TharinduMP on 10/13/2016.
 */
public abstract class AbstractRequest {

    @Autowired
    private ResourceServiceInterface resourceService;

    @Autowired
    private ProcessConfigServiceInterface processConfigService;

    private List<Resource> resourceList;
    private List<ProcessConfig> processConfigurationList;

    public void initialize(GenericRequest genericRequest) {
        this.resourceList = this.getProcessResources(genericRequest);
        this.processConfigurationList = this.getProcessConfigurations(genericRequest);
    }

    private List<Resource> getProcessResources(GenericRequest genericRequest) {
        return null;
    }

    private List<ProcessConfig> getProcessConfigurations(GenericRequest genericRequest) {
        return null;
    }

    public void process(GenericRequest genericRequest) {
        initialize(genericRequest);
    }
}
