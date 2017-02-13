package com.fintech.orion.api.service.validator;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sasitha on 2/10/17.
 *
 */
public class ResourceAccessValidator implements ResourceAccessValidatorInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Override
    public boolean validateResourceOwnership(String userName, List<String> resourceList) {
        boolean isAllResourceBelongsToTheUser = true;
        Client client = clientRepositoryInterface.findClientByUserName(userName);
        for (String resourceIdentificationCode : resourceList){
            Resource resource = resourceRepositoryInterface
                    .findResourceByResourceIdentificationCode(resourceIdentificationCode);
            isAllResourceBelongsToTheUser = client.getId().equals(resource.getClient().getId());
            if (!isAllResourceBelongsToTheUser){
                break;
            }
        }
        return isAllResourceBelongsToTheUser;
    }
}
