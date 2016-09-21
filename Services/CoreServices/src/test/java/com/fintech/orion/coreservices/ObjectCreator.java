package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;

public class ObjectCreator {

    public Client createClientObject() {
        return new Client();
    }

    public ClientLicense createClientLicenseObject() {
        return new ClientLicense();
    }

    public License createLicenseObject() {
        return new License();
    }

    public Process createProcessObject() {
        return new Process();
    }

    public ProcessingRequest createProcessingRequestObject() {
        return new ProcessingRequest();
    }

    public ProcessType createProcessTypeObject() {
        return new ProcessType();
    }

    public ProcessResource createProcessResourceObject(){
        return new ProcessResource();
    }

    public ProcessTypeLicense createProcessTypeLicenseObject(){
        return new ProcessTypeLicense();
    }

    public Resource createResourceObject(){
        return new Resource();
    }

    public ResourceType createResourceTypeObject(){
        return new ResourceType();
    }

    public Response createResponseObject() {
        return new Response();
    }
}
