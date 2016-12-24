package com.fintech.orion.api.service.client;

import com.fintech.orion.api.service.exceptions.ClientServiceException;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by sasitha on 11/3/16.
 */

public class ClientLicenseServiceTest {

    private static final String CLIENT_NAME = "zone";
    private static final String VALID_LICENSE = "e398ad4a-25a1-4b39-99ec-1f6c9f8a3ba4";
    private static final String INVALID_LICENSE = "e398ad4a-25a1-4b39-88ec-1f6c9f8a3ba4";

    Client client = new Client();
    License license = new License();
    private License invalidLicense = new License();
    List<License> licenseList = new ArrayList<>();

    @InjectMocks
    private ClientLicenseService clientLicenseService;

    @Mock
    private ClientRepositoryInterface clientRepositoryInterface;

    @Mock
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        client.setUserName(CLIENT_NAME);
        client.setId(1);
        license.setLicenseKey(VALID_LICENSE);
        license.setClient(client);
        invalidLicense.setLicenseKey(INVALID_LICENSE);
        invalidLicense.setClient(client);

        licenseList.add(license);
    }

    @Test
    public void should_return_the_correct_license_of_the_client() throws ClientServiceException, ItemNotFoundException, ParseException {

        Mockito.when(clientRepositoryInterface.findClientByUserName(Matchers.anyString())).thenReturn(client);
        Mockito.when(licenseRepositoryInterface.findLicensesByClientAndLessThanEndDateAndGreaterThanStartDate(
                Matchers.any(Client.class),
                Matchers.any(Date.class))).thenReturn(licenseList);
        Mockito.when(licenseRepositoryInterface.findLicenseByLicenseKey(Matchers.anyString())).thenReturn(license);
        Assert.assertEquals(VALID_LICENSE, clientLicenseService.getActiveLicenseOfClient(CLIENT_NAME));
    }


    @Test (expected = ClientServiceException.class)
    public void should_throw_a_ClientServiceException_if_no_license_found_for_the_client() throws ItemNotFoundException, ClientServiceException, ParseException {
        Mockito.when(clientRepositoryInterface.findClientByUserName(CLIENT_NAME)).thenReturn(client);
        Mockito.when(licenseRepositoryInterface.findLicensesByClientAndLessThanEndDateAndGreaterThanStartDate(client, new Date())).thenThrow(new ItemNotFoundException("No license found for the client"));
        String token = clientLicenseService.getActiveLicenseOfClient(CLIENT_NAME);
    }

    @Test (expected = ClientServiceException.class)
    public void should_throw_ClientServiceException_if_the_length_of_the_valid_license_list_is_zero() throws ItemNotFoundException, ClientServiceException, ParseException {
        Mockito.when(clientRepositoryInterface.findClientByUserName(CLIENT_NAME)).thenReturn(client);
        List<License> emptyLicenseList = new ArrayList<>();
        Mockito.when(licenseRepositoryInterface.findLicensesByClientAndLessThanEndDateAndGreaterThanStartDate(client, new Date())).thenReturn(emptyLicenseList);
        String token = clientLicenseService.getActiveLicenseOfClient(CLIENT_NAME);
    }

    @Test(expected = ClientServiceException.class)
    public void should_throw_ClientServiceException_if_there_are_no_clients_found_with_given_name() throws Exception, ClientServiceException {
        Mockito.when(clientRepositoryInterface.findClientByUserName(Matchers.anyString())).thenReturn(null);
        String token = clientLicenseService.getActiveLicenseOfClient(CLIENT_NAME);
    }

    @Test(expected = ClientServiceException.class)
    public void should_throw_ClientServiceException_if_no_license_list_found() throws Exception, ClientServiceException {
        Mockito.when(clientRepositoryInterface.findClientByUserName(CLIENT_NAME)).thenReturn(client);
        Mockito.when(licenseRepositoryInterface.findLicensesByClientAndLessThanEndDateAndGreaterThanStartDate(
                Matchers.any(Client.class),
                Matchers.any(Date.class))).thenThrow(new ItemNotFoundException("No license found "));
        String token = clientLicenseService.getActiveLicenseOfClient(CLIENT_NAME);
    }

}
