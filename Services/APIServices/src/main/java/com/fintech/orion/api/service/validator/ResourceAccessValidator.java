package com.fintech.orion.api.service.validator;

import com.fintech.orion.dataabstraction.entities.orion.AccountMapping;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.repositories.AccountMappingRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasitha on 2/10/17.
 *
 */
@Service
public class ResourceAccessValidator implements ResourceAccessValidatorInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    AccountMappingRepositoryInterface accountMappingRepositoryInterface;

    @Override
    @Transactional
    public boolean validateResourceOwnership(String userName, List<String> resourceList) {
        boolean isAllResourceBelongsToTheUser = true;
        Client client = clientRepositoryInterface.findClientByUserName(userName);
        for (String resourceIdentificationCode : resourceList){
            Resource resource = resourceRepositoryInterface
                    .findResourceByResourceIdentificationCode(resourceIdentificationCode);
            List<Integer> idsList = acountIds(client);
            isAllResourceBelongsToTheUser = idsList.contains(resource.getClient().getId());
            if (!isAllResourceBelongsToTheUser) {
                break;
            }
        }
        return isAllResourceBelongsToTheUser;
    }

    @Override
    @Transactional
    public boolean validateRequestOwnership(Client client, ProcessingRequest processingRequest) {
        boolean isAllResourceBelongsToTheUser = true;
        List<Integer> idsList = acountIds(client);
        isAllResourceBelongsToTheUser = idsList.contains(processingRequest.getClient().getId());
        return isAllResourceBelongsToTheUser;
    }
    
    @Override
    @Transactional
    public List<Integer> acountIds(Client client){
        List<AccountMapping> clientAccountsList = accountMappingRepositoryInterface.findClientAccountsListByClientID(client);
        ArrayList<Integer> idsList = new ArrayList<Integer>();
        idsList.add(client.getId());
        if (!clientAccountsList.isEmpty()) {
            for (AccountMapping am : clientAccountsList) {
                idsList.add(am.getClientByChild().getId());
            }
        }
        return idsList;
    }

}
