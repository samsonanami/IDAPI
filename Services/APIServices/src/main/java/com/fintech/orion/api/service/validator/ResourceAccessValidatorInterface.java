package com.fintech.orion.api.service.validator;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;

import java.util.List;

/**
 * Created by sasitha on 2/10/17.
 *
 */
public interface ResourceAccessValidatorInterface {
    boolean validateResourceOwnership(String userName, List<String> resourceList);
    boolean validateRequestOwnership(Client client, ProcessingRequest processingRequest);
    List<Integer> acountIds(Client client);
    
}
