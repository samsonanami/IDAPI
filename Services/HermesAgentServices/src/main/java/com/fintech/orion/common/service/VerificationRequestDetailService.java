package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import com.fintech.orion.dataabstraction.repositories.*;
import com.fintech.orion.dto.response.api.ImageDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Service
public class VerificationRequestDetailService implements VerificationRequestDetailServiceInterface{

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;


    @Autowired
    private ResponseRepositoryInterface responseRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Override
    @Transactional
    public ProcessingRequest getProcessingRequest(String processingRequestId) throws ItemNotFoundException {
        ProcessingRequest p = processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(processingRequestId);
        if (p == null){
            throw new ItemNotFoundException("No processing request found with processing request identification code : " + processingRequestId);
        }
        return p;
    }

    @Override
    @Transactional
    public void saveResponse(String rawResponse, String processedResponse, Process process) {
        Response response = new Response();
        response.setProcess(process);
        response.setRawJson(rawResponse);
        response.setExtractedJson(processedResponse);
        responseRepositoryInterface.save(response);
    }

    @Override
    @Transactional
    public void saveFinalVerificationResponse(String verificationResponse, String verificationRequestCode) {
        ProcessingRequest verificationRequest = processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(verificationRequestCode);
        verificationRequest.setFinalResponse(verificationResponse);
        verificationRequest.setProcessingCompletedOn(new Date());
        processingRequestRepositoryInterface.save(verificationRequest);
    }

    @Override
    @Transactional
    public boolean isVerificationProcessFoundInProcessingRequest(String processingRequestCode, String processType) {
        Process process = processRepositoryInterface.findProcessByProcessingRequestAndProcessType(processingRequestCode,
                processType);
        return process != null;
    }


    @Override
    @Transactional
    public List<Process> getProcessListBelongsToProcessingRequest(String processingRequestCode) {
        return processRepositoryInterface.findProcessByProcessingRequest(processingRequestCode);
    }

    @Override
    @Transactional
    public List<Process> getProcessListBelongsToProcessingRequest(String processingRequestCode, List<String> processTypes) {

        List<ProcessType> processTypeList = processTypeRepositoryInterface.findProcessTypesByTypeIn(processTypes);
        ProcessingRequest processingRequest = processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(processingRequestCode);
        return processRepositoryInterface.findProcessByProcessingRequestAndProcessTypeIn(processingRequest,
                processTypeList);
    }

    @Override
    @Transactional
    public ProcessType getProcessTypeFromProcessCode(String processIdentificationCode) {
        return processRepositoryInterface.test(processIdentificationCode);
    }


    @Override
    @Transactional
    public void updateProcessDetails(List<Process> processList) {
        processRepositoryInterface.save(processList);
    }

    @Override
    @Transactional
    public List<ImageDetail> getResourceOfProcessingRequest(String processingRequestCode) {
        List<ImageDetail> imageDetails = new ArrayList<>();
        for (Process process : this.processRepositoryInterface.findProcessByProcessingRequest(processingRequestCode)){
            imageDetails.addAll(getImageDetailsOfAProcess(process));
        }
        return imageDetails;
    }

    @Transactional
    @Override
    public List<VerificationProcessDetail> getVerificationProcessDetails(String processingRequestCode) {
        List<VerificationProcessDetail> verificationProcessDetails = new ArrayList<>();
        for (Process process : processRepositoryInterface.findProcessByProcessingRequest(processingRequestCode)){

            VerificationProcessDetail verificationProcessDetail = new VerificationProcessDetail();
            verificationProcessDetail.setVerificationProcessName(process.getProcessType().getType());
            verificationProcessDetail.setImageDetails(getImageDetailsOfAProcess(process));

            verificationProcessDetails.add(verificationProcessDetail);

        }
        return verificationProcessDetails;
    }

    @Transactional
    private List<ImageDetail> getImageDetailsOfAProcess(Process process){
        List<ImageDetail> imageDetails = new ArrayList<>();
        for (Resource resource : process.getResources()){
            ImageDetail imageDetail = new ImageDetail();
            imageDetail.setId(resource.getResourceIdentificationCode());
            imageDetail.setResourceName(resource.getResourceName().getName());
            imageDetails.add(imageDetail);
        }
        return imageDetails;
    }
}
