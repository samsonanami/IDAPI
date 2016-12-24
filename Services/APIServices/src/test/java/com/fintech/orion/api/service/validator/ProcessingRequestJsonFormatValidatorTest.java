package com.fintech.orion.api.service.validator;



import com.fintech.orion.dto.request.api.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by sasitha on 11/2/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessingRequestJsonFormatValidatorTest {

    @InjectMocks
    private ProcessingRequestJsonFormatValidator processingRequestJsonFormatValidator;


    private List<VerificationProcess> verificationProcessConfigList;


    /*
        configuration objects C stands for configuration
     */
    private Resource passportRC;
    private Resource dlRC;
    private List<Resource> idVerificationRCList;
    private List<Resource> addressVerificationRCList;
    private VerificationProcess idVerificationC;
    private VerificationProcess addressVerificationC;

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


    private void setupConfigurationValues(){
        passportRC = new Resource();
        passportRC.setResourceName("passport");
        passportRC.setResourceId("");
        dlRC = new Resource();
        dlRC.setResourceName("drivingLicenseFront");
        dlRC.setResourceId("");

        idVerificationRCList = new ArrayList<>();
        idVerificationRCList.add(passportRC);
        idVerificationRCList.add(dlRC);

        addressVerificationRCList = new ArrayList<>();
        addressVerificationRCList.add(dlRC);

        idVerificationC = new VerificationProcess();
        idVerificationC.setVerificationProcessType("idVerification");
        idVerificationC.setResources(idVerificationRCList);

        addressVerificationC = new VerificationProcess();
        addressVerificationC.setVerificationProcessType("addressVerification");
        addressVerificationC.setResources(addressVerificationRCList);

        verificationProcessConfigList = new ArrayList<>();
        verificationProcessConfigList.add(idVerificationC);
        verificationProcessConfigList.add(addressVerificationC);


        ReflectionTestUtils.setField(processingRequestJsonFormatValidator, "verificationProcessList", verificationProcessConfigList);

    }
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        passportR = new Resource();
        dlR = new Resource();
        idVerificationRList = new ArrayList<>();
        addressVerificationRList = new ArrayList<>();
        idVerificationR = new VerificationProcess();
        addressVerificationR= new VerificationProcess();
        verificationRequest = new VerificationRequest();
        verificationProcessList = new ArrayList<>();
        setupConfigurationValues();

        passportR.setResourceName("passport");
        passportR.setResourceId("12345");
        dlR.setResourceName("drivingLicenseFront");
        dlR.setResourceId("45678");
    }


    @Test
    public void should_return_true_if_json_structure_is_correct(){

        idVerificationRList.add(passportR);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);
        idVerificationR.setVerificationProcessType("idVerification");


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);


        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertTrue(isVerified);


    }

    @Test
    public void should_return_false_if_no_verification_processes_are_found(){
        Assert.assertFalse(processingRequestJsonFormatValidator.validate(verificationRequest));
    }

    @Test
    public void should_return_false_if_one_of_the_verification_process_type_is_incorrect(){

        idVerificationRList.add(passportR);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);
        idVerificationR.setVerificationProcessType("idVerificationInvalid");


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);


        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertFalse(isVerified);

    }

    @Test
    public void should_return_false_if_verification_process_type_is_null(){

        idVerificationRList.add(passportR);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);


        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertFalse(isVerified);
    }

    @Test
    public void should_return_false_if_one_of_the_verification_process_has_wrong_resource_name(){
        idVerificationRList.add(passportR);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);
        addressVerificationRList.add(passportRC);

        idVerificationR.setResources(idVerificationRList);
        idVerificationR.setVerificationProcessType("idVerificationInvalid");


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);
        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertFalse(isVerified);
    }

    @Test
    public void should_return_false_if_more_than_one_resource_from_same_resource_type_present_in_one_verification_request(){
        idVerificationRList.add(passportR);
        idVerificationRList.add(passportR);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);

        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertFalse(isVerified);
    }

    @Test
    public void should_return_false_if_one_or_more_resources_having_empty_resource_id(){
        Resource newResource = new Resource();
        newResource.setResourceName("passport");
        newResource.setResourceId("");
        idVerificationRList.add(newResource);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);
        idVerificationR.setVerificationProcessType("idVerification");


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);


        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertFalse(isVerified);
    }

    @Test
    public void should_return_false_if_one_or_more_resources_having_null_resource_id(){
        Resource newResource = new Resource();
        newResource.setResourceName("passport");
        idVerificationRList.add(newResource);
        idVerificationRList.add(dlR);

        addressVerificationRList.add(dlR);

        idVerificationR.setResources(idVerificationRList);
        idVerificationR.setVerificationProcessType("idVerification");


        addressVerificationR.setResources(addressVerificationRList);
        addressVerificationR.setVerificationProcessType("addressVerification");

        verificationProcessList.add(idVerificationR);
        verificationProcessList.add(addressVerificationR);

        verificationRequest.setVerificationProcesses(verificationProcessList);


        boolean isVerified = processingRequestJsonFormatValidator.validate(verificationRequest);
        Assert.assertFalse(isVerified);
    }

}
