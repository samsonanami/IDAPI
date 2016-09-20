package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import org.springframework.stereotype.Repository;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
@Repository
public class ClientLicenseRepository extends AbstractConfigDAO<ClientLicense, Integer> implements ClientLicenseRepositoryInterface {

    protected ClientLicenseRepository(Class<ClientLicense> entityClass) { super(entityClass); }

}
