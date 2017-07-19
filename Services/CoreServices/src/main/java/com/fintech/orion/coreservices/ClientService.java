package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Client entity service class
 */
@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ClientMapper clientMapper;


    @Transactional
    @Override
    public ClientDTO findByUserName(String userName) throws ItemNotFoundException {
        return clientMapper.clientToClientDTO(clientRepositoryInterface.findClientByUserName(userName));
    }
}
