package com.fintech.orion.mapping.clientlicense;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dto.clientlicense.ClientLicenseDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ClientLicense entity mapper
 */
@Mapper(componentModel = "spring")
public interface ClientLicenseMapper {

    ClientLicenseDTO clientToClientDTO(ClientLicense client);

    ClientLicense clientDTOToClient(ClientLicenseDTO clientDTO);

    List<ClientLicenseDTO> clientsToClientDTOs(List<ClientLicense> clients);
}
