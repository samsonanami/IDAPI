package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClientRepository extends AbstractDAO<Client, Integer> implements ClientRepositoryInterface {

    public ClientRepository() {
        super(Client.class);
    }

    @Override
    @Transactional
    public Client findByUserName(String username) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("userName", username));
        List<Client> clients = criteria.list();
        if(clients != null && !clients.isEmpty()){
            return clients.get(0);
        }else {
            throw new ItemNotFoundException("No valid user found for username : " + username);
        }
    }
}
