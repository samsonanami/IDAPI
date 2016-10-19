package com.fintech.orion.mapping.clientlicense;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dto.clientlicense.ClientLicenseDTO;
import com.fintech.orion.mapping.ObjectCreator;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.license.LicenseMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertEquals;

/**
 * ClientLicense entity mapper tests
 */
@Ignore
public class ClientLicenseMapperTest {

    @Test
    public void shouldMapClientLicenseToClientLicenseDTO(){
        ClientLicenseMapper clientLicenseMapper = Mappers.getMapper(ClientLicenseMapper.class);

        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
        LicenseMapper licenseMapper = Mappers.getMapper(LicenseMapper.class);

        ReflectionTestUtils.setField(clientLicenseMapper, "clientMapper", clientMapper);
        ReflectionTestUtils.setField(clientLicenseMapper, "licenseMapper", licenseMapper);

        ClientLicenseDTO clientLicenseDTO = ObjectCreator.aClientLicenseDTO();
        ClientLicense clientLicense = clientLicenseMapper.clientLicenseDTOToClientLicense(clientLicenseDTO);

        assertEquals(clientLicense.getId(), clientLicenseDTO.getId());
        assertEquals(clientLicense.getClient(), clientLicenseDTO.getClientDTO());
    }

    @Test
    public void shouldMapClientLicenseDTOToClientLicense(){
        ClientLicenseMapper clientLicenseMapper = Mappers.getMapper(ClientLicenseMapper.class);

        ClientLicense clientLicense = ObjectCreator.aClientLicense();
        ClientLicenseDTO clientLicenseDTO = clientLicenseMapper.clientLicenseToClientLicenseDTO(clientLicense);

        assertEquals(clientLicense.getId(), clientLicenseDTO.getId());
        assertEquals(clientLicense.getClient(), clientLicenseDTO.getClientDTO());
    }

}
