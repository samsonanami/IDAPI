package com.fintech.orion.dataabstraction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fintech.orion.dataabstraction.entities.orion.AccountMapping;
import com.fintech.orion.dataabstraction.entities.orion.AccountMappingId;
import com.fintech.orion.dataabstraction.entities.orion.Client;

public interface AccountMappingRepositoryInterface extends CrudRepository<AccountMapping, AccountMappingId> {

    @Query("select am from AccountMapping am where am.clientByParent = ?1")
    List<AccountMapping> findClientAccountsListByClientID(Client parent);

}
