package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository extends AbstractDAO<Client, Integer> implements ClientRepositoryInterface {

    public ClientRepository() {
        super(Client.class);
    }

    @Override
    public Client findByAuthToken(String authToken) throws ItemNotFoundException {
        List<Client> clients = findByCriteria(Restrictions.eq("authToken", authToken));
        if (clients != null && !clients.isEmpty()) {
            return clients.get(0);
        } else {
            throw new ItemNotFoundException("Client not found");
        }
    }
}
