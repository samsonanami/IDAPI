package com.fintech.orion.api.service.client;

import com.fintech.orion.api.service.exceptions.ClientServiceException;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
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
import java.util.List;

/**
 *
 * Created by sasitha on 11/3/16.
 */
public class ClientLicenseServiceTest {

    private static final String AUT_TOKEN = "e398ad4a-24a1-4b39-88ec-1f6c9f8a3ba4";
    private static final String VALID_LICENSE = "e398ad4a-25a1-4b39-99ec-1f6c9f8a3ba4";

    Client client = new Client();
    License license = new License();
    List<License> licenseList = new ArrayList<>();

    @InjectMocks
    ClientLicenseService clientLicenseService;

    @Mock
    private ClientRepositoryInterface clientRepositoryInterface;

    @Mock
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        license.setLicenseKey(VALID_LICENSE);
    }

    @Test
    public void should_return_the_correct_license_of_the_client() throws ClientServiceException, ItemNotFoundException {
        licenseList.add(license);
        Mockito.when(clientRepositoryInterface.findByAuthToken(AUT_TOKEN)).thenReturn(client);
        Mockito.when(licenseRepositoryInterface.getCurrentlyActiveLicenseListOfClient(client)).thenReturn(licenseList);
        Assert.assertEquals(VALID_LICENSE, clientLicenseService.getActiveLicenseOfClient(AUT_TOKEN));
    }

    @Test (expected = ClientServiceException.class)
    public void should_throw_a_clientServiceException_if_no_clients_found_for_given_auth_token() throws ClientServiceException, ItemNotFoundException{
        licenseList.add(license);
        Mockito.when(clientRepositoryInterface.findByAuthToken(AUT_TOKEN)).thenThrow(new ItemNotFoundException("Client not found"));
        Mockito.when(licenseRepositoryInterface.getCurrentlyActiveLicenseListOfClient(client)).thenReturn(licenseList);
        String token = clientLicenseService.getActiveLicenseOfClient(AUT_TOKEN);
    }

    @Test (expected = ClientServiceException.class)
    public void should_throw_a_ClientServiceException_if_no_license_found_for_the_client() throws ItemNotFoundException, ClientServiceException {
        Mockito.when(clientRepositoryInterface.findByAuthToken(AUT_TOKEN)).thenReturn(client);
        Mockito.when(licenseRepositoryInterface.getCurrentlyActiveLicenseListOfClient(client)).thenThrow(new ItemNotFoundException("No license found for the client"));
        String token = clientLicenseService.getActiveLicenseOfClient(AUT_TOKEN);
    }

    @Test (expected = ClientServiceException.class)
    public void should_throw_ClientServiceException_if_the_length_of_the_valid_license_list_is_zero() throws ItemNotFoundException, ClientServiceException {
        Mockito.when(clientRepositoryInterface.findByAuthToken(AUT_TOKEN)).thenReturn(client);
        List<License> emptyLicenseList = new ArrayList<>();
        Mockito.when(licenseRepositoryInterface.getCurrentlyActiveLicenseListOfClient(client)).thenReturn(emptyLicenseList);
        String token = clientLicenseService.getActiveLicenseOfClient(AUT_TOKEN);
    }

}
