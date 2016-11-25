package com.fintech.orion.api.service.validator;

import com.fintech.orion.api.service.exceptions.ClientLicenseValidatorException;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationprocess.Resource;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Created by sasitha on 11/2/16.
 */
public class ClientLicenseValidatorServiceTest {



    @InjectMocks
    private  ClientLicenseValidatorService validatorService;

    @Mock
    private ClientRepositoryInterface clientRepositoryInterface;

    @Mock
    private LicenseRepositoryInterface licenseRepositoryInterface;

    private static final String AUTH_TOKEN = "e398ad4a-24a1-4b39-88ec-1f6c9f8a3ba4";
    private static final String ID_VERIFICATION = "idVerification";
    private static final String VALID_LICENSE = "e398ad4a-25a1-4b39-99ec-1f6c9f8a3ba4";
    private static final String INVALID_LICENSE = "m998ad4a-34a1-4b39-99ec-1f6c9f8a3ba4";

    String authToken;
    ProcessingRequest processingRequest;
    List<VerificationProcess> verificationProcessList;
    VerificationProcess verificationProcess;
    List<Resource> resourceList;
    Resource resource;

    Client client = new Client();
    License license = new License();
    Set<ProcessTypeLicense> processTypeLicenses;
    ProcessType processType1;
    ProcessTypeLicense processTypeLicense1;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        authToken = "";
        resource = new Resource();
        processingRequest = new ProcessingRequest();
        verificationProcessList = new ArrayList<>();
        verificationProcess = new VerificationProcess();
        verificationProcess.setVerificationProcessType(ID_VERIFICATION);
        verificationProcessList.add(verificationProcess);
        processingRequest.setVerificationProcesses(verificationProcessList);
        resourceList = new ArrayList<>();

        processType1 = new ProcessType();
        processTypeLicenses = new HashSet<>();
        processTypeLicense1 = new ProcessTypeLicense();

        processType1.setType(ID_VERIFICATION);
        processTypeLicense1.setProcessType(processType1);
        processTypeLicenses.add(processTypeLicense1);
        license.setLicenseKey(VALID_LICENSE);
        license.setProcessTypeLicenses(processTypeLicenses);

    }

    @Test
    public void should_return_true_for_valid_processing_request_of_a_client_having_valid_license() throws ClientLicenseValidatorException, ItemNotFoundException {
        Mockito.when(licenseRepositoryInterface.getLicenseByLicenseKey(VALID_LICENSE)).thenReturn(license);
        Assert.assertTrue(validatorService.validate(VALID_LICENSE, processingRequest));
    }

    @Test (expected = ClientLicenseValidatorException.class)
    public void should_throw_ClientLicenseValidatorException_if_no_license_were_found_for_given_license_key() throws ItemNotFoundException, ClientLicenseValidatorException {
        Mockito.when(licenseRepositoryInterface.getLicenseByLicenseKey(INVALID_LICENSE)).thenThrow(new ItemNotFoundException("No license found"));
        validatorService.validate(INVALID_LICENSE, processingRequest);
    }


}
