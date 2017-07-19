package com.fintech.orion.api.service.validator;


import com.fintech.orion.dto.request.api.Resource;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.request.api.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by sasitha on 11/2/16.
 */

public class ProcessingRequestJsonFormatValidator implements ProcessingRequestJsonFormatValidatorInterface{

    @Autowired
    private List<VerificationProcess> verificationProcessList;

    public boolean validate(VerificationRequest processingRequest) {
        boolean status = false;
        if(processingRequest.getVerificationProcesses() != null){
            status = validateProcessingRequest(processingRequest);
        }
        return status;
    }

    private boolean validateProcessingRequest(VerificationRequest processingRequest) {
        boolean status = false;
        for (VerificationProcess process : processingRequest.getVerificationProcesses()){
            status = false;
            if(process.getVerificationProcessType() != null && findOccurrencesOfVerificationProcessByName(verificationProcessList, process.getVerificationProcessType()) > 0){
                status = validateVerificationProcess(process);
                if(!status){
                    break;
                }
            }else {
                break;
            }
        }
        return status;
    }

    private boolean validateVerificationProcess(VerificationProcess process) {
        boolean status;
        status = false;
        for (Resource r : process.getResources()){
            status = false;
            if(r.getResourceId() != null && !r.getResourceId().isEmpty() && findOccurrencesOfResourceByName(process.getResources(), r.getResourceName()) ==
                    findOccurrencesOfResourceByName(getResourceListFromConfigurations(process.getVerificationProcessType()), r.getResourceName())){
                status = true;
            }else {
                break;
            }
        }
        return status;
    }

    private int findOccurrencesOfResourceByName(List<Resource> resourceList, String resourceName){
        int count = 0;
        for (Resource r : resourceList){
            if(resourceName.equals(r.getResourceName())){
                count ++;
            }
        }
        return count;
    }

    private int findOccurrencesOfVerificationProcessByName(List<VerificationProcess> processList, String processType){
        int count = 0;
        for (VerificationProcess v : processList){
            if(processType.equals(v.getVerificationProcessType())){
                count ++;
            }
        }
        return count;
    }

    private List<Resource> getResourceListFromConfigurations(String processType){
        List<Resource> resourceList = new ArrayList<>();
        for (VerificationProcess v : verificationProcessList){
            if(processType.equals(v.getVerificationProcessType())){
                resourceList = v.getResources();
            }
        }
        return resourceList;
    }

}
