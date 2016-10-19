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
        return clientMapper.clientsToClientDTOs(getAll());
    }

    @Transactional
    @Override
    public ClientDTO findById(int id) throws ItemNotFoundException {
        return clientMapper.clientToClientDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ClientDTO clientDTO) {
        saveOrUpdate(clientMapper.clientDTOToClient(clientDTO));
    }

    @Transactional
    @Override
    public void delete(ClientDTO clientDTO) {
        delete(clientMapper.clientDTOToClient(clientDTO));
    }

    @Transactional
    @Override
    public ClientDTO findByAuthToken(String authToken) throws ItemNotFoundException {
        Client client = clientRepositoryInterface.findByAuthToken(authToken);
        return clientMapper.clientToClientDTO(client);
    }
}
