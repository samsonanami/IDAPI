package com.fintech.orion.api.service.request;

import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;
import com.fintech.orion.api.service.exceptions.ResourceNotFoundException;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.repositories.*;
import com.fintech.orion.dto.request.api.Resource;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.request.api.VerificationRequest;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.external.VerificationResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/23/16.
 */
public class ProcessingRequestServiceTest {

    @InjectMocks
    ProcessingRequestService processingRequestService;

    @Mock
    private ClientRepositoryInterface clientRepositoryInterface;

    @Mock
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Mock
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Mock
    private ProcessRepositoryInterface processRepositoryInterface;

    @Mock
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Mock
    private ResourceNameRepositoryInterface resourceNameRepositoryInterface;

    @Mock
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;


    private Client client = new Client();
    private ProcessingRequest processingRequest = new ProcessingRequest();
    private ProcessingStatus processingStatus = new ProcessingStatus();
    private Process process = new Process();

    private ResourceName passportResourceName = new ResourceName();
    private ResourceName drivingLicenseResourceName = new ResourceName();
    private String extractedJson = "{\"status\":\"passed\",\"verificationRequestId\":\"9a65c287-b700-4f63-8320-fab7e828d458\",\"facialVerification\":null,\"livenessTest\":null,\"idVerification\":null,\"addressVerification\":null,\"data\":[{\"id\":\"surname\",\"value\":[{\"id\":\"passport##surname\",\"value\":\"SMITH\",\"confidence\":91}],\"comparison\":[]},{\"id\":\"given_names\",\"value\":[{\"id\":\"passport##given_names\",\"value\":\"NAOMI ANN\",\"confidence\":90}],\"comparison\":[]},{\"id\":\"date_of_birth\",\"value\":[{\"id\":\"passport##date_of_birth\",\"value\":\"01 FEA/ FEB 1983\",\"confidence\":83}],\"comparison\":[]},{\"id\":\"place_of_birth\",\"value\":[{\"id\":\"passport##place_of_birth\",\"value\":\"BAILE ATHA CLIATH/DUBLIN\",\"confidence\":90}],\"comparison\":[]},{\"id\":\"date_of_issue\",\"value\":[{\"id\":\"passport##date_of_issue\",\"value\":\"15 AIB/APR 201 1\",\"confidence\":86}],\"comparison\":[]},{\"id\":\"issuing_authorithy\",\"value\":[{\"id\":\"passport##issuing_authorithy\",\"value\":\"IRL\",\"confidence\":91}],\"comparison\":[]},{\"id\":\"date_of_expiry\",\"value\":[{\"id\":\"passport##date_of_expiry\",\"value\":\"14 AIB/APR 2021\",\"confidence\":89}],\"comparison\":[]},{\"id\":\"MRZ_Line\",\"value\":[],\"comparison\":[]},{\"id\":\"family_name\",\"value\":[],\"comparison\":[]},{\"id\":\"parents_given_name\",\"value\":[],\"comparison\":[]},{\"id\":\"sex\",\"value\":[{\"id\":\"passport##sex\",\"value\":\"F\",\"confidence\":87}],\"comparison\":[]},{\"id\":\"passport_number\",\"value\":[{\"id\":\"passport##passport_number\",\"value\":\"PD0524371\",\"confidence\":90}],\"comparison\":[]},{\"id\":\"nationality\",\"value\":[{\"id\":\"passport##nationality\",\"value\":\"EIREANNACH/IRISH\",\"confidence\":70}],\"comparison\":[]},{\"id\":\"place_of_issue\",\"value\":[{\"id\":\"passport##place_of_issue\",\"value\":\"\",\"confidence\":0}],\"comparison\":[]},{\"id\":\"MRZ_line1\",\"value\":[{\"id\":\"passport##MRZ_line1\",\"value\":\"P<IRLSMITH<<NAOMI<ANN<<<<<<<<<<<<<<<<<<<<<<<\",\"confidence\":91}],\"comparison\":[]},{\"id\":\"MRZ_line2\",\"value\":[{\"id\":\"passport##MRZ_line2\",\"value\":\"PD05243712IRL8302010F2104142<<<<<<<<<<<<<<<0\",\"confidence\":86}],\"comparison\":[]},{\"id\":\"address_line1\",\"value\":[],\"comparison\":[]},{\"id\":\"processing_failure\",\"value\":[{\"id\":\"passport##processing_failure\",\"value\":\"\",\"confidence\":0}],\"comparison\":[]},{\"id\":\"address_line2\",\"value\":[],\"comparison\":[]},{\"id\":\"address_line3\",\"value\":[],\"comparison\":[]},{\"id\":\"title\",\"value\":[],\"comparison\":[]},{\"id\":\"name_in_utility_bill\",\"value\":[],\"comparison\":[]},{\"id\":\"utility_bill_document_name\",\"value\":[],\"comparison\":[]},{\"id\":\"utility_bill_date\",\"value\":[],\"comparison\":[]},{\"id\":\"TemplateName\",\"value\":[{\"id\":\"passport##TemplateName\",\"value\":\"P_Ireland2\",\"confidence\":0}],\"comparison\":[]}],\"imageDetails\":[],\"verificationDetails\":[]}";


    com.fintech.orion.dataabstraction.entities.orion.Resource resourceEntity = new com.fintech.orion.dataabstraction.entities.orion.Resource();
    /*
       request body objects R stands for request
    */
    private Resource passportR;
    private Resource dlR;
    private List<Resource> idVerificationRList;
    private List<Resource> addressVerificationRList;
    private VerificationProcess idVerificationR;
    private VerificationProcess addressVerificationR;
    private List<VerificationProcess> verificationProcessList;
    private VerificationRequest verificationRequest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.setId(1);
        client.setUserName("zone");

        passportR = new Resource();
        dlR = new Resource();
        idVerificationRList = new ArrayList<>();
        addressVerificationRList = new ArrayList<>();
        idVerificationR = new VerificationProcess();
        addressVerificationR= new VerificationProcess();
        verificationRequest = new VerificationRequest();
        verificationProcessList = new ArrayList<>();

        passportR.setResourceName("passport");
        passportR.setResourceId("12345");
        dlR.setResourceName("drivingLicenseFront");
        dlR.setResourceId("45678");

        idVerificationRList.add(passportR);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);
        idVerificationR.setVerificationProcessType("idVerification");


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);


    }

    @Test
    public void should_return_save_the_verification_process_and_return_verification_process_id() throws Exception{
        Mockito.when(clientRepositoryInterface.findClientByUserName("zone")).thenReturn(client);
        Mockito.when(processingRequestRepositoryInterface.save(processingRequest)).thenReturn(processingRequest);
        Mockito.when(processingStatusRepositoryInterface.findProcessingStatusByStatus("ProcessingRequested")).thenReturn(processingStatus);
        Mockito.when(resourceRepositoryInterface.findResourceByResourceIdentificationCode(Matchers.anyString())).thenReturn(resourceEntity);
        String verificationProcessId = processingRequestService.saveVerificationProcessData("zone", verificationProcessList);

        Assert.assertNotNull(verificationProcessId);
    }

    @Test
    public void should_return_detail_response() throws Exception{
        processingRequest.setClient(client);
        Set<Process> processSet = new HashSet<>();
        processSet.add(process);
        processingRequest.setProcesses(processSet);
        Mockito.when(clientRepositoryInterface.findClientByUserName("zone")).thenReturn(client);
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(Matchers.anyString())).thenReturn(processingRequest);

        VerificationResponse response = processingRequestService.getDetailedResponse("zone", "12345");
        Assert.assertNotNull(response);
        Assert.assertEquals("pending", response.getStatus());
    }

    @Test
    public void should_return_detail_response_with_correct_data()throws Exception{
        processingRequest.setClient(client);
        Response response = new Response();
        response.setExtractedJson(extractedJson);
        response.setRawJson(extractedJson);
        process.setResponse(response);
        Set<Process> processSet = new HashSet<>();
        processSet.add(process);
        processingRequest.setProcesses(processSet);
        processingRequest.setFinalResponse(extractedJson);
        Mockito.when(clientRepositoryInterface.findClientByUserName("zone")).thenReturn(client);
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(Matchers.anyString())).thenReturn(processingRequest);


        VerificationResponse fullResponse = processingRequestService.getDetailedResponse("zone", "12345");

        Assert.assertNotNull(fullResponse);
        Assert.assertEquals("passed", fullResponse.getStatus());
    }

    @Test(expected = ResourceAccessPolicyViolationException.class)
    public void should_throw_ResourceAccessPolicyViolationException_if_client_is_different_from_owner_of_the_request()throws Exception{
        processingRequest.setClient(client);
        Set<Process> processSet = new HashSet<>();
        processSet.add(process);
        processingRequest.setProcesses(processSet);
        Client secondUser = new Client();
        secondUser.setUserName("second_user");
        Mockito.when(clientRepositoryInterface.findClientByUserName("second_user")).thenReturn(secondUser);
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(Matchers.anyString())).thenReturn(processingRequest);

        VerificationResponse response = processingRequestService.getDetailedResponse("second_user", "12345");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_ResourceNotFoundException_if_no_processing_request_found_with_given_id()throws Exception{
        Mockito.when(clientRepositoryInterface.findClientByUserName("zone")).thenReturn(client);
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(Matchers.anyString())).thenReturn(null);

        VerificationResponse response = processingRequestService.getDetailedResponse("second_user", "12345");

    }

}