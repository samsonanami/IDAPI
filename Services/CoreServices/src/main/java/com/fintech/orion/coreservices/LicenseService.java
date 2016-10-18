package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.License;
import org.springframework.stereotype.Service;

/**
 * License entity service class
 */
@Service
public class LicenseService extends AbstractService<License, Integer> implements LicenseServiceInterface {

}
