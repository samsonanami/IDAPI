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
    private String extractedJson = "{\"facialMatch\":null,\"livenessTest\":null,\"verificationRequestId\":\"b2629276-d043-40da-95b9-47df5bc85c2d\",\"status\":\"processing_successful\",\"data\":[{\"id\":\"surname\",\"value\":[{\"id\":\"passport##surname\",\"value\":\"SORRELL\",\"confidence\":84},{\"id\":\"drivingLicenseFront##surname\",\"value\":\"SORFIELL\",\"confidence\":79}],\"comparison\":[{\"id\":\"passport##surnamevsdrivingLicenseFront##surname\",\"value\":false},{\"id\":\"drivingLicenseFront##surnamevspassport##surname\",\"value\":false}]},{\"id\":\"given_name\",\"value\":[{\"id\":\"passport##given_name\",\"value\":\"PHILIP MARK\",\"confidence\":91},{\"id\":\"drivingLicenseFront##given_name\",\"value\":\"PHILIP MARK\",\"confidence\":87}],\"comparison\":[{\"id\":\"passport##given_namevsdrivingLicenseFront##given_name\",\"value\":true},{\"id\":\"drivingLicenseFront##given_namevspassport##given_name\",\"value\":true}]},{\"id\":\"date_of_birth\",\"value\":[{\"id\":\"passport##date_of_birth\",\"value\":\"20 JAN_ /JAN 59\",\"confidence\":92},{\"id\":\"drivingLicenseFront##date_of_birth\",\"value\":\"20.01.1959\",\"confidence\":89}],\"comparison\":[{\"id\":\"passport##date_of_birthvsdrivingLicenseFront##date_of_birth\",\"value\":false},{\"id\":\"drivingLicenseFront##date_of_birthvspassport##date_of_birth\",\"value\":false}]},{\"id\":\"place_of_birth\",\"value\":[{\"id\":\"passport##place_of_birth\",\"value\":\"SOUTHBND —ON-SEA\",\"confidence\":84},{\"id\":\"drivingLicenseFront##place_of_birth\",\"value\":\"UNITED KINGDOM\",\"confidence\":87}],\"comparison\":[{\"id\":\"passport##place_of_birthvsdrivingLicenseFront##place_of_birth\",\"value\":false},{\"id\":\"drivingLicenseFront##place_of_birthvspassport##place_of_birth\",\"value\":false}]},{\"id\":\"date_of_issue\",\"value\":[{\"id\":\"passport##date_of_issue\",\"value\":\"09 FEB /FEV 12\",\"confidence\":90},{\"id\":\"drivingLicenseFront##date_of_issue\",\"value\":\"05.05.2016\",\"confidence\":89}],\"comparison\":[{\"id\":\"passport##date_of_issuevsdrivingLicenseFront##date_of_issue\",\"value\":false},{\"id\":\"drivingLicenseFront##date_of_issuevspassport##date_of_issue\",\"value\":false}]},{\"id\":\"issuing_authorithy\",\"value\":[{\"id\":\"passport##issuing_authorithy\",\"value\":\"IPS\",\"confidence\":94},{\"id\":\"drivingLicenseFront##issuing_authorithy\",\"value\":\"DVLA\",\"confidence\":86}],\"comparison\":[{\"id\":\"passport##issuing_authorithyvsdrivingLicenseFront##issuing_authorithy\",\"value\":false},{\"id\":\"drivingLicenseFront##issuing_authorithyvspassport##issuing_authorithy\",\"value\":false}]},{\"id\":\"date_of_expiry\",\"value\":[{\"id\":\"passport##date_of_expiry\",\"value\":\"09 NOV /NOV 22\",\"confidence\":93},{\"id\":\"drivingLicenseFront##date_of_expiry\",\"value\":\"04.05.2026\",\"confidence\":87}],\"comparison\":[{\"id\":\"passport##date_of_expiryvsdrivingLicenseFront##date_of_expiry\",\"value\":false},{\"id\":\"drivingLicenseFront##date_of_expiryvspassport##date_of_expiry\",\"value\":false}]},{\"id\":\"MRZ_Line\",\"value\":[{\"id\":\"drivingLicenseFront##MRZ_Line\",\"value\":\"SOHFIESOIZOQPMSJP &\",\"confidence\":67}],\"comparison\":[]},{\"id\":\"family_name\",\"value\":[],\"comparison\":[]},{\"id\":\"parents_given_name\",\"value\":[],\"comparison\":[]},{\"id\":\"sex\",\"value\":[{\"id\":\"passport##sex\",\"value\":\"M\",\"confidence\":90}],\"comparison\":[]},{\"id\":\"passport_number\",\"value\":[{\"id\":\"passport##passport_number\",\"value\":\"761335999\",\"confidence\":86}],\"comparison\":[]},{\"id\":\"nationality\",\"value\":[{\"id\":\"passport##nationality\",\"value\":\"BRITISH CITIZEN\",\"confidence\":92}],\"comparison\":[]},{\"id\":\"place_of_issue\",\"value\":[{\"id\":\"passport##place_of_issue\",\"value\":\"GBR\",\"confidence\":91}],\"comparison\":[]},{\"id\":\"MRZ_line1\",\"value\":[{\"id\":\"passport##MRZ_line1\",\"value\":\"P<GBRSORRELL<<PHI LI P<MARK<<<<<<<<<<<<<<<<<<<\",\"confidence\":90}],\"comparison\":[]},{\"id\":\"MRZ_line2\",\"value\":[{\"id\":\"passport##MRZ_line2\",\"value\":\"7613359992GBR5901205M2211097<<<<<<<<<<<<<<04\",\"confidence\":81}],\"comparison\":[]},{\"id\":\"address\",\"value\":[],\"comparison\":[]},{\"id\":\"processing_failure\",\"value\":[{\"id\":\"passport##processing_failure\",\"value\":\"\",\"confidence\":0},{\"id\":\"drivingLicenseFront##processing_failure\",\"value\":\"\",\"confidence\":0}],\"comparison\":[{\"id\":\"passport##processing_failurevsdrivingLicenseFront##processing_failure\",\"value\":true},{\"id\":\"drivingLicenseFront##processing_failurevspassport##processing_failure\",\"value\":true}]}],\"visualAuthenticity\":[],\"imageQuality\":[],\"dataValidation\":[],\"idDocFullValidations\":[],\"addressDocFullValidations\":[]}";


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

        VerificationProcessDetailedResponse response = processingRequestService.getDetailedResponse("zone", "12345");
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatus(), "Pending");
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

        Mockito.when(clientRepositoryInterface.findClientByUserName("zone")).thenReturn(client);
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(Matchers.anyString())).thenReturn(processingRequest);


        VerificationProcessDetailedResponse fullResponse = processingRequestService.getDetailedResponse("zone", "12345");

        Assert.assertNotNull(fullResponse);
        Assert.assertEquals(fullResponse.getStatus(), "processing_successful");
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

        VerificationProcessDetailedResponse response = processingRequestService.getDetailedResponse("second_user", "12345");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_ResourceNotFoundException_if_no_processing_request_found_with_given_id()throws Exception{
        Mockito.when(clientRepositoryInterface.findClientByUserName("zone")).thenReturn(client);
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(Matchers.anyString())).thenReturn(null);

        VerificationProcessDetailedResponse response = processingRequestService.getDetailedResponse("second_user", "12345");

    }

}