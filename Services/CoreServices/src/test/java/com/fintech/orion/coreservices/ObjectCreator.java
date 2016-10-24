package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.resource.ResourceDTO;

/**
 * Object creator for service classes
 */
public class ObjectCreator {

    private ObjectCreator() {}

    public static Client createClientObject() {
        return new Client();
    }

    public static License createLicenseObject() {
        return new License();
    }

    public static Process createProcessObject() {
        return new Process();
    }

    public static ProcessingRequest createProcessingRequestObject() {
        return new ProcessingRequest();
    }

    public static ProcessType createProcessTypeObject() {
        return new ProcessType();
    }

    public static ProcessTypeLicense createProcessTypeLicenseObject(){
        return new ProcessTypeLicense();
    }

    public static Resource createResourceObject(){
        return new Resource();
    }

    public static ResourceType createResourceTypeObject(){
        return new ResourceType();
    }

    public static Response createResponseObject() {
        return new Response();
    }

    public static ProcessingStatus createProcessingStatusObject() { return new ProcessingStatus(); }

    public static ClientDTO createClientDTOObject() {
        return new ClientDTO();
    }

    public static ProcessDTO createProcessDTOObject() {
        return new ProcessDTO();
    }

    public static ResourceDTO createResourceDTOObject() {
        return new ResourceDTO();
    }

    public static ProcessTypeDTO createProcessTypeDTOObject() {
        return new ProcessTypeDTO();
    }

    public static ProcessingRequestDTO createProcessingRequestDTOObject() {
        return new ProcessingRequestDTO();
    }

    public static ProcessingStatusDTO createProcessingStatusDTOObject() {
        return new ProcessingStatusDTO();
    }
}
