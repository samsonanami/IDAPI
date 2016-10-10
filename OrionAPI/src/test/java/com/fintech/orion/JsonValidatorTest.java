package com.fintech.orion;

import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.helper.JsonValidator;
import com.fintech.orion.helper.JsonValidatorInterface;
import com.fintech.orion.dataabstraction.models.verificationprocess.Resource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class JsonValidatorTest {

    private static final String RESOURCE_ID = "123456";
    private static final String ID_VERIFICATION = "idVerification";
    private List<VerificationProcess> verificationProcessList = new ArrayList<>();
    private static final String VERIFICATION_PROCESS_LIST = "verificationProcessList";
    private ProcessingRequest processingRequest;

    @Before
    public void setup(){
        List<Resource> resourceList = new ArrayList<>();

        Resource resource = new Resource();
        resource.setResourceId("123456789");
        resourceList.add(resource);
        VerificationProcess verificationProcess = new VerificationProcess();
        verificationProcess.setVerificationProcessType(ID_VERIFICATION);
        verificationProcess.setResources(resourceList);

        verificationProcessList.add(verificationProcess);
        processingRequest = new ProcessingRequest();
        processingRequest.setVerificationProcesses(verificationProcessList);
    }

    @Test
    public void shouldReturnTrueForIdVerificationJsonWhenJsonValidateCalled() throws ReflectiveOperationException {
        JsonValidatorInterface jsonValidatorInterface = new JsonValidator();
        ReflectionTestUtils.setField(jsonValidatorInterface, VERIFICATION_PROCESS_LIST, verificationProcessList);

        List<VerificationProcess> verificationProcesses = new ArrayList<>();
        VerificationProcess process = new VerificationProcess();
        process.setVerificationProcessType(ID_VERIFICATION);
        List<Resource> resourceList1 = new ArrayList<>();
        Resource resource1 = new Resource();
        resource1.setResourceId(RESOURCE_ID);
        resourceList1.add(resource1);
        process.setResources(resourceList1);

        verificationProcesses.add(process);

        assertTrue(jsonValidatorInterface.jsonValidate(verificationProcesses));
    }

    @Test
    public void shouldReturnFalseForIdVerificationJsonWhenJsonValidateCalledWithOutResourceIdValue() throws ReflectiveOperationException {
        JsonValidatorInterface jsonValidatorInterface = new JsonValidator();
        ReflectionTestUtils.setField(jsonValidatorInterface, VERIFICATION_PROCESS_LIST, verificationProcessList);

        List<VerificationProcess> verificationProcesses = new ArrayList<>();
        VerificationProcess process = new VerificationProcess();
        process.setVerificationProcessType(ID_VERIFICATION);
        List<Resource> resourceList1 = new ArrayList<>();
        Resource resource1 = new Resource();
        resourceList1.add(resource1);
        process.setResources(resourceList1);

        verificationProcesses.add(process);

        assertFalse(jsonValidatorInterface.jsonValidate(verificationProcesses));
    }

    @Test
    public void shouldReturnFalseForIdVerificationJsonWhenJsonValidateCalledWithUnwantedValues() throws ReflectiveOperationException {
        JsonValidatorInterface jsonValidatorInterface = new JsonValidator();
        ReflectionTestUtils.setField(jsonValidatorInterface, VERIFICATION_PROCESS_LIST, verificationProcessList);

        List<VerificationProcess> verificationProcesses = new ArrayList<>();
        VerificationProcess process = new VerificationProcess();
        process.setVerificationProcessType(ID_VERIFICATION);
        List<Resource> resourceList1 = new ArrayList<>();
        Resource resource1 = new Resource();
        resource1.setResourceId(RESOURCE_ID);
        resource1.setResourceName("name");
        resourceList1.add(resource1);
        process.setResources(resourceList1);

        verificationProcesses.add(process);

        assertFalse(jsonValidatorInterface.jsonValidate(verificationProcesses));
    }

    @Test
    public void shouldReturnFalseForIdVerificationJsonWhenJsonValidateCalledWithFaultProcessType() throws ReflectiveOperationException {
        JsonValidatorInterface jsonValidatorInterface = new JsonValidator();
        ReflectionTestUtils.setField(jsonValidatorInterface, VERIFICATION_PROCESS_LIST, verificationProcessList);

        List<VerificationProcess> verificationProcesses = new ArrayList<>();
        VerificationProcess process = new VerificationProcess();
        process.setVerificationProcessType("test");
        List<Resource> resourceList1 = new ArrayList<>();
        Resource resource1 = new Resource();
        resource1.setResourceId(RESOURCE_ID);
        resourceList1.add(resource1);
        process.setResources(resourceList1);

        verificationProcesses.add(process);

        assertFalse(jsonValidatorInterface.jsonValidate(verificationProcesses));
    }

}
