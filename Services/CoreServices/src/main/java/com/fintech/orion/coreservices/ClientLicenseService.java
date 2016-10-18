package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.clientlicense.ClientLicenseDTO;
import com.fintech.orion.mapping.clientlicense.ClientLicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClientLicense entity service class
 */
@Service
public class ClientLicenseService extends AbstractService<ClientLicense, Integer> implements ClientLicenseServiceInterface {

    @Autowired
    private ClientLicenseMapper clientLicenseMapper;

    @Override
    public List<ClientLicenseDTO> getAllDTOs() {
        return clientLicenseMapper.clientsToClientDTOs(getAll());
    }

    @Override
    public ClientLicenseDTO findById(int id) throws ItemNotFoundException {
        return clientLicenseMapper.clientToClientDTO(findById(new Integer(id)));
    }

    @Override
    public void saveOrUpdate(ClientLicenseDTO clientLicenseDTO) {
        saveOrUpdate(clientLicenseMapper.clientDTOToClient(clientLicenseDTO));
    }

    @Override
    public void delete(ClientLicenseDTO clientLicenseDTO) {
        delete(clientLicenseMapper.clientDTOToClient(clientLicenseDTO));
    }
}
