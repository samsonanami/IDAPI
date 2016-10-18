package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import org.springframework.stereotype.Service;

/**
 * ClientLicense entity service class
 */
@Service
public class ClientLicenseService extends AbstractService<ClientLicense, Integer> implements ClientLicenseServiceInterface {

}
