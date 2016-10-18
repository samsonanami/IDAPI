package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Client entity service class
 */
@Service
public class ClientService extends AbstractService<Client, Integer> implements ClientServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @Transactional
    @Override
    public ClientDTO findByAuthToken(String authToken) throws ItemNotFoundException {
        Client client = clientRepositoryInterface.findByAuthToken(authToken);
        return clientMapper.clientToClientDTO(client);
    }
}
