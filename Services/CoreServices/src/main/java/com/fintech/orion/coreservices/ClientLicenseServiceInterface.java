package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.clientlicense.ClientLicenseDTO;

import java.util.List;

/**
 * ClientLicense entity service interface
 */
public interface ClientLicenseServiceInterface extends ServiceInterface<ClientLicense, Integer> {

    List<ClientLicenseDTO> getAllDTOs();

    ClientLicenseDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ClientLicenseDTO clientLicenseDTO);

    void delete(ClientLicenseDTO clientLicenseDTO);

}
