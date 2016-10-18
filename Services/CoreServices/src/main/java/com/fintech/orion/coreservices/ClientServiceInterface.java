package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;

import java.util.List;

/**
 * Client entity service interface
 */
public interface ClientServiceInterface extends ServiceInterface<Client, Integer> {

    List<ClientDTO> getAllDTOs();

    ClientDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ClientDTO e);

    void delete(ClientDTO e);

    ClientDTO findByAuthToken(String authToken) throws ItemNotFoundException;

}
