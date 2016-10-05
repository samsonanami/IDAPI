package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import org.springframework.stereotype.Repository;

@Repository
public class ClientLicenseRepository extends AbstractDAO<ClientLicense, Integer> implements ClientLicenseRepositoryInterface {

    public ClientLicenseRepository() {
        super(ClientLicense.class);
    }

}
