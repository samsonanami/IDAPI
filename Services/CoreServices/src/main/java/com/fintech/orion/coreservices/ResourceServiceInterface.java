package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

/**
 * Resource entity service interface
 */
public interface ResourceServiceInterface extends ServiceInterface<Resource, Integer> {

    Resource save(String newFilename, String uuidNumber, String contentType, String accessToken) throws ItemNotFoundException;

    Resource findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException;

}
