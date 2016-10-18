package com.fintech.orion.mapping;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.clientlicense.ClientLicenseDTO;
import com.fintech.orion.dto.license.LicenseDTO;

/**
 * Mapping object creations
 */
public class ObjectCreator {

    private ObjectCreator() {
    }

    public static ClientDTO aClientDTO(){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1);
        clientDTO.setEmail("test@test.com");
        clientDTO.setAuthToken("12345");
        clientDTO.setRegisteredOn(GenerateTimestamp.timestamp());
        clientDTO.setUserName("test");
        clientDTO.setPassword("test");
        clientDTO.setRefreshToken("98765");
        clientDTO.setEnnabled(false);
        return clientDTO;
    }

    public static Client aClient() {
        Client client = new Client();
        client.setId(1);
        client.setEmail("test@test.com");
        client.setAuthToken("12345");
        client.setRegisteredOn(GenerateTimestamp.timestamp());
        client.setUserName("test");
        client.setPassword("test");
        client.setRefreshToken("98765");
        client.setEnnabled(false);
        return client;
    }

    public static LicenseDTO aLicenseDTO(){
        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setId(1);
        licenseDTO.setStartDate(GenerateTimestamp.timestamp());
        licenseDTO.setEndDate(GenerateTimestamp.timestamp());
        licenseDTO.setRequestCount(100);
        return licenseDTO;
    }

    public static License aLicense(){
        License license = new License();
        license.setId(1);
        license.setStartDate(GenerateTimestamp.timestamp());
        license.setEndDate(GenerateTimestamp.timestamp());
        license.setRequestCount(100);
        return license;
    }

    public static ClientLicenseDTO aClientLicenseDTO() {
        ClientLicenseDTO clientLicenseDTO = new ClientLicenseDTO();
        clientLicenseDTO.setId(1);
        clientLicenseDTO.setClientDTO(aClientDTO());
        clientLicenseDTO.setLicenseDTO(aLicenseDTO());
        return clientLicenseDTO;
    }

    public static ClientLicense aClientLicense() {
        ClientLicense clientLicense = new ClientLicense();
        clientLicense.setId(1);
        clientLicense.setClient(aClient());
        clientLicense.setLicense(aLicense());
        return clientLicense;
    }
}
