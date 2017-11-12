package com.fintech.orion.api.service.request;

import com.fintech.orion.api.service.exceptions.DataNotFoundException;
import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;
import com.fintech.orion.api.service.exceptions.ResourceNotFoundException;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.*;
import com.fintech.orion.dto.request.api.Resource;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.api.VerificationRequestSummery;
import com.fintech.orion.dto.response.external.VerificationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProcessingRequestService implements ProcessingRequestServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    private ResourceNameRepositoryInterface resourceNameRepositoryInterface;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Autowired
    private ResponseRepositoryInterface responseRepositoryInterface;

    @Transactional(rollbackFor = {ItemNotFoundException.class, DataNotFoundException.class})
    @Override
    public String saveVerificationProcessData(String clientName, List<VerificationProcess> verificationProcessList) throws DataNotFoundException {
        Client client = clientRepositoryInterface.findClientByUserName(clientName);

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setClient(client);
        processingRequest.setReceivedOn(new Date());
        processingRequest.setProcessingRequestIdentificationCode(UUID.randomUUID().toString());
        processingRequestRepositoryInterface.save(processingRequest);

        ProcessingStatus initialStatus = processingStatusRepositoryInterface.findProcessingStatusByStatus(Status.PROCESSING_REQUESTED.getStatus());

        for (VerificationProcess v : verificationProcessList){
            Process process = new Process();
            process.setProcessIdentificationCode(UUID.randomUUID().toString());
            process.setProcessingRequest(processingRequest);
            process.setProcessingStatus(initialStatus);
            process.setProcessType(processTypeRepositoryInterface.findProcessTypeByType(v.getVerificationProcessType()));
            processRepositoryInterface.save(process);

            updateResources(v.getResources(), process);

        }
        return processingRequest.getProcessingRequestIdentificationCode();
    }

    @Transactional
    private void updateResources(List<Resource> resourceList, Process process) throws DataNotFoundException {
        for (Resource r: resourceList){
            com.fintech.orion.dataabstraction.entities.orion.Resource resourceEntity = resourceRepositoryInterface.findResourceByResourceIdentificationCode(r.getResourceId());
            if (resourceEntity == null){
                throw new DataNotFoundException("No resource found with resource identification code :" +
                        r.getResourceId() + " and could not be associated with resource name  : " +
                        r.getResourceName() + " to complete the processing.");
            }
            ResourceName resourceName = resourceNameRepositoryInterface.findResourceNameByName(r.getResourceName());
            resourceEntity.setProcess(process);
            resourceEntity.setResourceName(resourceName);
            resourceRepositoryInterface.save(resourceEntity);
        }
    }

    @Override
    @Transactional
    public VerificationResponse getDetailedResponse(String clientName, String verificationRequestId) throws IOException, ResourceAccessPolicyViolationException, ResourceNotFoundException {

        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        ProcessingRequest processingRequest = processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(verificationRequestId);
        if (processingRequest == null){
            throw new ResourceNotFoundException("No processing request found with identification code : " + verificationRequestId);
        }
        if(!processingRequest.getClient().getUserName().equals(client.getUserName())){
            throw new ResourceAccessPolicyViolationException("Accessing content not belong to the requested user is denied by resource access policy.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        VerificationResponse response = new VerificationResponse();

        if(processingRequest.getFinalResponse() != null){
            String processedJson = processingRequest.getFinalResponse();
            response = objectMapper.readValue(processedJson, VerificationResponse.class);
        }else {
            response.setVerificationRequestId(verificationRequestId);
            response.setStatus("pending");
        }
        return response;
    }

    @Override
    @Transactional
    public PagedResources<VerificationRequestSummery> verificationRequestSummery(String clientName, Date from, Date to,
                                                                                 String page, String size) throws DataNotFoundException {
        Client client = clientRepositoryInterface.findClientByUserName(clientName);

        Page<ProcessingRequest> processingRequests;
        Pageable pageable = new PageRequest(
                Integer.valueOf(page), Integer.valueOf(size), Sort.Direction.DESC, "id"
        );
        processingRequests =  processingRequestRepositoryInterface.findProcessingRequestByClient(client, pageable);


        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata( Integer.valueOf(size), Integer.valueOf(page),
                processingRequests.getTotalElements(), processingRequests.getTotalPages());

        return new PagedResources<>(getVerificationSummeryList(processingRequests.getContent()), pageMetadata,
                getLink(processingRequests, "page", "size"));
    }

    private List<VerificationRequestSummery> getVerificationSummeryList(List<ProcessingRequest> processingRequestList){
        List<VerificationRequestSummery> verificationRequestSummeryList = new ArrayList<>();
        for (ProcessingRequest processingRequest : processingRequestList){
            VerificationRequestSummery summery = new VerificationRequestSummery();
            summery.setRequestIdentificationCode(processingRequest.getProcessingRequestIdentificationCode());
            summery.setRequestedDate(processingRequest.getReceivedOn());
            summery.setProcessingCompletedOn(processingRequest.getProcessingCompletedOn());
            summery.setProcessedString(processingRequest.getFinalResponse());
            verificationRequestSummeryList.add(summery);
        }
        return verificationRequestSummeryList;
    }

    private List<Link> getLink(Page page, String pageParam, String sizeParam){
        List<Link> linkList = new ArrayList<>();

        if(page.hasPrevious()){
            String path = createBuilder()
                    .queryParam(pageParam,page.getNumber()-1)
                    .queryParam(sizeParam,page.getSize())
                    .build()
                    .toUriString();
            Link link = new Link(path, Link.REL_PREVIOUS);
            linkList.add(link);
        }

        if(page.hasNext()){
            String path = createBuilder()
                    .queryParam(pageParam,page.getNumber()+1)
                    .queryParam(sizeParam,page.getSize())
                    .build()
                    .toUriString();
            Link link = new Link(path, Link.REL_NEXT);
            linkList.add(link);
        }

        linkList.add( buildPageLink(pageParam,0,sizeParam,page.getSize(),Link.REL_FIRST));

        int indexOfLastPage = page.getTotalPages() - 1;
        linkList.add(buildPageLink(pageParam,indexOfLastPage,sizeParam,page.getSize(),Link.REL_LAST));

        linkList.add(buildPageLink(pageParam,page.getNumber(),sizeParam,page.getSize(),Link.REL_SELF));

        return linkList;
    }

    private ServletUriComponentsBuilder createBuilder() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri();
    }

    private Link buildPageLink(String pageParam,int page,String sizeParam,int size,String rel) {
        String path = createBuilder()
                .queryParam(pageParam,page)
                .queryParam(sizeParam,size)
                .build()
                .toUriString();
        return new Link(path,rel);
    }
    
    /*
     * This method will update the existing verification data in the database
     */
    @Transactional
    @Override
    public String updateVerificationRequestData(String clientName, String verificationId, VerificationResponse body)
            throws ItemNotFoundException, JsonProcessingException {
        ProcessingRequest processingRequestEntity = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(verificationId);

        ProcessingStatus processingStatus = processingStatusRepositoryInterface
                .findProcessingStatusByStatusIgnoreCase(body.getStatus());
        processingRequestEntity.setFinalVerificationStatus(processingStatus);
        processingRequestRepositoryInterface.save(processingRequestEntity);

        List<Process> processEntity = processRepositoryInterface
                .findProcessByProcessingRequest(processingRequestEntity.getProcessingRequestIdentificationCode());
        for (Process process : processEntity) {
            com.fintech.orion.dataabstraction.entities.orion.Response responseEntity = responseRepositoryInterface
                    .findProcessByProcessingIdentificationCode(process.getId());
            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = objectWriter.writeValueAsString(body);
            responseEntity.setRawJson(json);
            responseRepositoryInterface.save(responseEntity);

        }
        return verificationId;
    }

}
