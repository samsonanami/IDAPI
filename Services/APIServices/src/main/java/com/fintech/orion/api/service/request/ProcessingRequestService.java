package com.fintech.orion.api.service.request;

import com.fintech.orion.api.service.exceptions.DataNotFoundException;
import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;
import com.fintech.orion.api.service.exceptions.ResourceNotFoundException;
import com.fintech.orion.api.service.validator.ResourceAccessValidator;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.*;
import com.fintech.orion.dto.request.api.Resource;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.response.api.ProcessingRequestStatusResponse;
import com.fintech.orion.dto.response.api.VerificationRequestSummery;
import com.fintech.orion.dto.response.external.Data;
import com.fintech.orion.dto.response.external.DataValues;
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
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProcessingRequestService implements ProcessingRequestServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Autowired
    ProcessingRequestStatusRepositoryInterface processingRequestStatusRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    private ResourceNameRepositoryInterface resourceNameRepositoryInterface;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Autowired
    private ResourceAccessValidator resourceAccessValidator;

    @PersistenceContext
    private EntityManager manager;
    
    @Autowired
    private String lockedOnExpiry;
    
    @Autowired
    private String surname;
    
    @Autowired
    private String givenname;
   
    public String query;

    @Transactional(rollbackFor = { ItemNotFoundException.class, DataNotFoundException.class })
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

        if (!resourceAccessValidator.validateRequestOwnership(client, processingRequest)) {
            throw new ResourceAccessPolicyViolationException(
                    "Accessing content not belong to the requested user is denied by resource access policy.");
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
    public boolean getProcessingRequestLockedStatus(String verificationId, String clientName){
        boolean status = false;
        ProcessingRequest processingRequest = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(verificationId);
        ProcessingRequestStatus processingRequestStatus = processingRequestStatusRepositoryInterface
                .findProcessingRequestStatusByProcessingRequest(processingRequest);
        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        if(processingRequestStatus != null &&  processingRequestStatus.getStatus().equals("locked") && client.getId().equals(processingRequestStatus.getClient().getId())) {
            status = true;
        }
        return status;
    }
    
    @Override
    @Transactional
    public boolean getProcessingRequestLockedStatusByClient(String verificationId, String clientName){
        boolean status = false;
        ProcessingRequest processingRequest = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(verificationId);
        ProcessingRequestStatus processingRequestStatus = processingRequestStatusRepositoryInterface
                .findProcessingRequestStatusByProcessingRequest(processingRequest);
        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        if(processingRequestStatus != null &&  processingRequestStatus.getStatus().equals("unlocked") && client.getId().equals(processingRequestStatus.getClient().getId())) {
            status = true;
        }else
        	if(processingRequestStatus != null &&  processingRequestStatus.getStatus().equals("locked") && client.getId().equals(processingRequestStatus.getClient().getId())) {
                status = true;
            }
        return status;
    }
    @Override
    @Transactional
    public boolean getProcessingRequestUnLockedStatus(String verificationId, String clientName) {
        boolean status = false;
        Integer lockedOnExp =  Integer.valueOf(lockedOnExpiry);
        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        ProcessingRequest processingRequest = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(verificationId);
        ProcessingRequestStatus processingRequestStatus = processingRequestStatusRepositoryInterface
                .findProcessingRequestStatusByProcessingRequest(processingRequest);
        if(processingRequestStatus != null && processingRequestStatus.getStatus().equals("locked") && getLockedOnTimeInMin(processingRequestStatus.getLockedOn()) < lockedOnExp && client != processingRequestStatus.getClient()) {
            status = true;
        }
        
        return status;
    }
    
   

    @Override
    @Transactional
    public PagedResources<VerificationRequestSummery> verificationRequestSummery(String principalName,
            String search, Date from, Date to, String page, String size, List<String> statusList)
            throws DataNotFoundException {
        Client client = clientRepositoryInterface.findClientByUserName(principalName);
        List<ProcessingStatus> processingStatusList = new ArrayList<ProcessingStatus>();
        for (String pr : statusList) {
            ProcessingStatus processingStatus = processingStatusRepositoryInterface
                    .findProcessingStatusByStatusIgnoreCase(pr);

            processingStatusList.add(processingStatus);
        }
        Page<ProcessingRequest> processingRequests = null;
        Pageable pageable = new PageRequest(Integer.valueOf(page), Integer.valueOf(size), Sort.Direction.DESC, "id");
        List<Integer> ids = resourceAccessValidator.acountIds(client);
        List<Client> clients = clientRepositoryInterface.findClientsById(ids);
        processingRequests = buildFilteringQuery(clients, search, from, to, processingStatusList, pageable);
        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata(Integer.valueOf(size),
                Integer.valueOf(page), processingRequests.getTotalElements(), processingRequests.getTotalPages());

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
            summery.setFinalVerificationStatus(processingRequest.getFinalVerificationStatus().getStatus());
            summery.setClientName(processingRequest.getClientName());
            summery.setRequestIdentificationCode(processingRequest.getProcessingRequestIdentificationCode());
            summery.setRequestStatus(requestStatus(processingRequest));
            verificationRequestSummeryList.add(summery);
        }
        return verificationRequestSummeryList;
    }
    
    @Transactional
    private boolean requestStatus(ProcessingRequest processingRequest) {
        boolean status = false;
        ProcessingRequestStatus processingRequestStatus = processingRequestStatusRepositoryInterface
                .findProcessingRequestStatusByProcessingRequest(processingRequest);
        if (processingRequestStatus != null) {
            if(processingRequestStatus.getStatus().equals("locked") ) {
                status = true;
            }
            
        }
        else {
            status = false;  
        }
        return status;
    }

    private List<Link> getLink(Page page, String pageParam, String sizeParam) {
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
     * Building query based on the filtering parameters received
     */
    public Page<ProcessingRequest> buildFilteringQuery(List<Client> clients,String clientName, Date from, Date to,
            List<ProcessingStatus> status, Pageable pageable) {
        Page<ProcessingRequest> processingRequests = null;

        if (clientName != null && clientName.length() != 0 && !status.isEmpty() && from == null && to == null) {
            processingRequests = processingRequestRepositoryInterface.filterProcessingRequestByFilteringFrom(status,
                    clientName, clients, pageable);
        }
        if (from != null && to != null && (clientName == null || clientName.length() == 0) && status.isEmpty()) {
            processingRequests = processingRequestRepositoryInterface.filterProcessingRequestByFilteringFromAndTo(
                    getTimestamp(from), getTimestampWithExtraHours(to), clients, pageable);
        }
        if (from != null && to != null && clientName != null && clientName.length() != 0 && status.isEmpty()) {

            processingRequests = processingRequestRepositoryInterface.filterProcessingRequestByFilteringClient(
                    getTimestamp(from), getTimestampWithExtraHours(to), clientName, clients, pageable);
        }
        if (from != null && to != null && clientName != null && clientName.length() != 0 && !status.isEmpty()) {
            processingRequests = processingRequestRepositoryInterface.filterProcessingRequestByFilteringAll(status,
                    getTimestamp(from), getTimestampWithExtraHours(to), clientName, clients, pageable);
        }
        if (from != null && to != null && !status.isEmpty() && (clientName == null || clientName.length() == 0)) {
            processingRequests = processingRequestRepositoryInterface.filterProcessingRequestByFilteringStatus(status,
                    getTimestamp(from), getTimestampWithExtraHours(to), clients, pageable);
        }
        if (from == null && to == null && clientName != null && clientName.length() != 0 && status.isEmpty()) {
            processingRequests = processingRequestRepositoryInterface
                    .filterProcessingRequestByFilteringclientName(clientName, clients, pageable);
        }
        if (from == null && to == null && (clientName == null || clientName.length() == 0) && !status.isEmpty()) {
            processingRequests = processingRequestRepositoryInterface.filterProcessingRequestByFilteringStatus(status ,clients, pageable);
        }
        return processingRequests;

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
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(body);
        processingRequestEntity.setFinalVerificationStatus(processingStatus);
        processingRequestEntity.setFinalResponse(json);
        processingRequestEntity.setClientName(processClientName(body.getData()));
        processingRequestRepositoryInterface.save(processingRequestEntity);
        return verificationId;
    }

    
    /*
     * This method will update the existing status data in the database
     */
    @Transactional
    @Override
    public ProcessingRequestStatusResponse updateProcessingRequestStatus(String clientName, String verificationId, String status)
            throws DataNotFoundException, JsonProcessingException {
    	Integer lockedOnExp =  Integer.valueOf(lockedOnExpiry);
        ProcessingRequest processingRequestEntity = processingRequestRepositoryInterface
                .findProcessingRequestByProcessingRequestIdentificationCode(verificationId);
        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        ProcessingRequestStatus processingRequestStatus = processingRequestStatusRepositoryInterface
                .findProcessingRequestStatusByProcessingRequest(processingRequestEntity);
        boolean lockedBySameOperator= getProcessingRequestLockedStatus(verificationId, clientName);
        ProcessingRequestStatusResponse processingRequestStatusResponse = new ProcessingRequestStatusResponse();
        /*
         * First time lock for the verification id
         */
        if (processingRequestStatus == null && status.equals("locked")) {
            processingRequestStatus = new ProcessingRequestStatus();
            processingRequestStatus.setStatus(status);
            processingRequestStatus.setLockedOn(new Date());
            processingRequestStatus.setClient(client);
            processingRequestStatus.setProcessingRequest(processingRequestEntity);
            processingRequestStatusRepositoryInterface.save(processingRequestStatus);
            processingRequestStatusResponse.setStatus(status);
            processingRequestStatusResponse.setMessage("Processing Request identification code is "+ status +" successfully");
        }
        /*
         * Locking the request id which in unlocked state. This will update the exiting record.
         */
        if (processingRequestStatus != null && status.equals("locked") && processingRequestStatus.getStatus().equals("unlocked")) {
            processingRequestStatus.setStatus(status);
            processingRequestStatus.setLockedOn(new Date());
            processingRequestStatus.setClient(client);
            processingRequestStatus.setProcessingRequest(processingRequestEntity);
            processingRequestStatusRepositoryInterface.save(processingRequestStatus);
            processingRequestStatusResponse.setStatus(status);
            processingRequestStatusResponse.setMessage("Processing Request identification code is "+ status +" successfully");  
        }
        /*
         * Acquiring the lock which is already locked but expired due to time limit 
         */
        if (processingRequestStatus != null && status.equals("locked") && getLockedOnTimeInMin(processingRequestStatus.getLockedOn()) >= lockedOnExp && processingRequestStatus.getStatus().equals("locked")) {
            processingRequestStatus.setStatus(status);
            processingRequestStatus.setLockedOn(new Date());
            processingRequestStatus.setClient(client);
            processingRequestStatus.setProcessingRequest(processingRequestEntity);
            processingRequestStatusRepositoryInterface.save(processingRequestStatus);
            processingRequestStatusResponse.setStatus(status);
            processingRequestStatusResponse.setMessage("Processing Request identification code is "+ status +" successfully");     
        }
        /*
         * Acquiring the un-lock which is already un-locked but same client login
         */
        if (processingRequestStatus != null && status.equals("unlocked")  && processingRequestStatus.getStatus().equals("unlocked") && client.getId().equals(processingRequestStatus.getClient().getId())) {
            processingRequestStatus.setStatus(status);
            processingRequestStatus.setLockedOn(new Date());
            processingRequestStatus.setClient(client);
            processingRequestStatus.setProcessingRequest(processingRequestEntity);
            processingRequestStatusRepositoryInterface.save(processingRequestStatus);
            processingRequestStatusResponse.setStatus(status);
            processingRequestStatusResponse.setMessage("Processing Request identification code is "+ status +" successfully");     
        }
     
        /*
         * Acquiring the lock which is already locked but same client login
         */
        if (processingRequestStatus != null && status.equals("locked") && processingRequestStatus.getStatus().equals("locked") && client.getId().equals(processingRequestStatus.getClient().getId())) {
            processingRequestStatus.setStatus(status);
            processingRequestStatus.setLockedOn(new Date());
            processingRequestStatus.setClient(client);
            processingRequestStatus.setProcessingRequest(processingRequestEntity);
            processingRequestStatusRepositoryInterface.save(processingRequestStatus);
            processingRequestStatusResponse.setStatus(status);
            processingRequestStatusResponse.setMessage("Processing Request identification code is "+ status +" successfully");     
        }
        /*
         * Un-Locking the request id which is already locked by same person
         */
        if(status.equals("unlocked") && lockedBySameOperator) {
            processingRequestStatus.setStatus(status);
            processingRequestStatus.setUnlockedOn(new Date());
            processingRequestStatus.setClient(client);
            processingRequestStatus.setProcessingRequest(processingRequestEntity);
            processingRequestStatusRepositoryInterface.save(processingRequestStatus);   
            processingRequestStatusResponse.setStatus(status);
            processingRequestStatusResponse.setMessage("Processing Request identification code is "+ status +" successfully");
        }
        return processingRequestStatusResponse;
    }

    private Timestamp getTimestampWithExtraHours(java.util.Date date) {
        final long hoursInMillis = 60L * 60L * 1000L;
        Date newDate = new Date(date.getTime() + 
                (24L * hoursInMillis));
        return new java.sql.Timestamp(newDate.getTime());
    }
    
    private Timestamp getTimestamp(java.util.Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }

    private int getLockedOnTimeInMin(Date lockedOn) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lockOn = dateFormat.format(lockedOn);
        long difference;
        long diffMinutes = 0;
        Date date1;
        try {
            date1 = (Date) dateFormat.parse(lockOn);
            Date date2 = (Date) dateFormat.parse(dateFormat.format(date));
            difference = date2.getTime() - date1.getTime();
            diffMinutes = difference / (60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) diffMinutes;

    }
    
    private String processClientName(List<Data> input) {
        String clientName = "";
        for (Data data : input) {
            if (data.getId().equalsIgnoreCase(surname)) {
                clientName += processSurname(data.getValue());
                clientName += " ";
            }
            if (data.getId().equalsIgnoreCase(givenname)) {
                clientName += processGivenname(data.getValue());
            }
        }
        return clientName ;

    }
    
    private String processSurname(List<DataValues> input) {
        String surname ="";
        int count = 0;
        for (DataValues dataValues : input) {
            String tempName = dataValues.getValue();
            if (tempName != null && !tempName.equals("") && count < 1) {
                surname += tempName;
                count++;
            }
        }
        return surname;
        
    }
    
    private String processGivenname(List<DataValues> input) {
        String givenname ="";
        int count = 0;
        for (DataValues dataValues : input) {
            String tempName = dataValues.getValue();
            if (tempName != null && !tempName.equals("") && count < 1) {
                givenname += tempName;
                count++;
            }
        }
        return givenname;
        
    }
}
