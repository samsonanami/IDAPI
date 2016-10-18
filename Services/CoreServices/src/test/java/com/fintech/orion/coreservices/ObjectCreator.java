package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.client.ClientDTO;

/**
 * Object creator for service classes
 */
public class ObjectCreator {

    private ObjectCreator() {}

    public static Client createClientObject() {
        return new Client();
    }

    public static ClientLicense createClientLicenseObject() {
        return new ClientLicense();
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

    public static ProcessResource createProcessResourceObject(){
        return new ProcessResource();
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
}
