package com.fintech.orion.api.service.validator;


import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationprocess.Resource;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by sasitha on 11/2/16.
 */
public class ProcessingRequestJsonFormatValidatorTest {


    private static final String RESOURCE_ID = "123456";
    private static final String ID_VERIFICATION = "idVerification";
    private List<VerificationProcess> verificationProcessList = new ArrayList<>();
    private static final String VERIFICATION_PROCESS_LIST = "verificationProcessList";
    private ProcessingRequest processingRequest;
    private ProcessingRequestJsonFormatValidatorInterface processingRequestJsonFormatValidator;


    private ProcessingRequest processingRequestToValidate;
    private  List<VerificationProcess> verificationProcessToValidate;
    private VerificationProcess verificationProcess1;
    private VerificationProcess verificationProcess2;
    private VerificationProcess verificationProcess3;
    private List<Resource> resourceList1;
    Resource resource1;

    @Before
    public void setup() {
        setupAutowiredFields();
        processingRequestToValidate = new ProcessingRequest();
        verificationProcess1 = new VerificationProcess();
        verificationProcess2 = new VerificationProcess();
        verificationProcess3 = new VerificationProcess();
        verificationProcessToValidate = new ArrayList<>();

        verificationProcess1.setVerificationProcessType(ID_VERIFICATION);
        verificationProcess2.setVerificationProcessType("verificationType2");
        verificationProcess3.setVerificationProcessType(ID_VERIFICATION);


        resourceList1 = new ArrayList<>();
        resource1 = new Resource();
        resource1.setResourceId(RESOURCE_ID);
        resource1.setResourceName("id");
        resourceList1.add(resource1);
        verificationProcess1.setResources(resourceList1);
        verificationProcess2.setResources(resourceList1);
        verificationProcess3.setResources(resourceList1);
    }

    @Test
    public void should_return_true_if_json_structure_is_correct(){

        verificationProcessToValidate.add(verificationProcess1);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertTrue(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }

    @Test
    public void should_return_false_if_no_verification_processes_are_found(){
        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }

    @Test
    public void should_return_false_if_one_of_the_verification_process_type_is_incorrect(){


        verificationProcessToValidate.add(verificationProcess1);
        verificationProcessToValidate.add(verificationProcess2);
        verificationProcessToValidate.add(verificationProcess3);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));

    }

    @Test
    public void should_return_false_if_verification_process_type_is_null(){

        verificationProcessToValidate.add(verificationProcess1);
        verificationProcessToValidate.add(verificationProcess2);
        verificationProcessToValidate.add(verificationProcess3);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }

    @Test
    public void should_return_false_if_one_of_the_verification_process_has_wrong_resource_name(){
        Resource resource = new Resource();
        resource.setResourceName("Front image");
        resource.setResourceId(RESOURCE_ID);

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);

        verificationProcess1.setResources(resourceList1);
        verificationProcess2.setResources(resourceList);
        verificationProcess3.setResources(resourceList1);

        verificationProcessToValidate.add(verificationProcess1);
        verificationProcessToValidate.add(verificationProcess2);
        verificationProcessToValidate.add(verificationProcess3);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }

    @Test
    public void should_return_false_if_more_than_one_resource_from_same_resource_type_present_in_one_verification_request(){
        Resource resource = new Resource();
        resource.setResourceName("id");
        resource.setResourceId(RESOURCE_ID);

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);

        verificationProcess1.setResources(resourceList1);
        verificationProcess2.setResources(resourceList);
        verificationProcess3.setResources(resourceList1);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }

    @Test
    public void should_return_false_if_one_or_more_resources_having_empty_resource_id(){
        Resource resource = new Resource();
        resource.setResourceName("id");
        resource.setResourceId("");

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);

        verificationProcess2.setVerificationProcessType(ID_VERIFICATION);

        verificationProcess1.setResources(resourceList1);
        verificationProcess2.setResources(resourceList);
        verificationProcess3.setResources(resourceList1);

        verificationProcessToValidate.add(verificationProcess1);
        verificationProcessToValidate.add(verificationProcess2);
        verificationProcessToValidate.add(verificationProcess3);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }

    @Test
    public void should_return_false_if_one_or_more_resources_having_null_resource_id(){
        Resource resource = new Resource();
        resource.setResourceName("id");

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);

        verificationProcess2.setVerificationProcessType(ID_VERIFICATION);

        verificationProcess1.setResources(resourceList1);
        verificationProcess2.setResources(resourceList);
        verificationProcess3.setResources(resourceList1);

        verificationProcessToValidate.add(verificationProcess1);
        verificationProcessToValidate.add(verificationProcess2);
        verificationProcessToValidate.add(verificationProcess3);

        processingRequestToValidate.setVerificationProcesses(verificationProcessToValidate);

        Assert.assertFalse(processingRequestJsonFormatValidator.validate(processingRequestToValidate));
    }




    private void setupAutowiredFields(){
        List<Resource> resourceList = new ArrayList<>();
        Resource resource = new Resource();
        resource.setResourceId("123456789");
        resource.setResourceName("id");
        resourceList.add(resource);
        VerificationProcess verificationProcess = new VerificationProcess();
        verificationProcess.setVerificationProcessType(ID_VERIFICATION);
        verificationProcess.setResources(resourceList);

        verificationProcessList.add(verificationProcess);
        processingRequest = new ProcessingRequest();
        processingRequest.setVerificationProcesses(verificationProcessList);

        processingRequestJsonFormatValidator = new ProcessingRequestJsonFormatValidator();
        ReflectionTestUtils.setField(processingRequestJsonFormatValidator, VERIFICATION_PROCESS_LIST, verificationProcessList);
    }


}
