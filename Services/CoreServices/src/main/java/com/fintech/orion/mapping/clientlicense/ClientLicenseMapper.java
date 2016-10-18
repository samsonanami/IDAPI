package com.fintech.orion.mapping.clientlicense;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dto.clientlicense.ClientLicenseDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.license.LicenseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * ClientLicense entity mapper
 */
@Mapper(uses = {ClientMapper.class, LicenseMapper.class}, componentModel = "spring")
public interface ClientLicenseMapper {

    @Mappings({
            @Mapping(target = "clientDTO", source = "client"),
            @Mapping(target = "licenseDTO", source = "license")
    })
    ClientLicenseDTO clientLicenseToClientLicenseDTO(ClientLicense clientLicense);

    ClientLicense clientLicenseDTOToClientLicense(ClientLicenseDTO clientLicenseDTO);

    List<ClientLicenseDTO> clientLicensesToClientLicenseDTOs(List<ClientLicense> clientLicenses);
}
