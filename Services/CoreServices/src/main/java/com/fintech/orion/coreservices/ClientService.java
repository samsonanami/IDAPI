package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Client entity service class
 */
@Service
public class ClientService extends AbstractService<Client, Integer> implements ClientServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ClientMapper clientMapper;

    @Transactional
    @Override
    public List<ClientDTO> getAllDTOs() {
        return clientMapper.clientsToClientDTOs(clientRepositoryInterface.getAll());
    }

    @Transactional
    @Override
    public ClientDTO findById(int id) throws ItemNotFoundException {
        return clientMapper.clientToClientDTO(clientRepositoryInterface.findById(id));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ClientDTO clientDTO) {
        clientRepositoryInterface.saveOrUpdate(clientMapper.clientDTOToClient(clientDTO));
    }

    @Transactional
    @Override
    public void delete(ClientDTO clientDTO) {
        clientRepositoryInterface.delete(clientMapper.clientDTOToClient(clientDTO));
    }

    @Transactional
    @Override
    public ClientDTO findByAuthToken(String authToken) throws ItemNotFoundException {
        return clientMapper.clientToClientDTO(clientRepositoryInterface.findByAuthToken(authToken));
    }
}
