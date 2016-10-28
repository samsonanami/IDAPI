package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceRepository extends AbstractDAO<Resource, Integer> implements ResourceRepositoryInterface {

    protected ResourceRepository() {
        super(Resource.class);
    }

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Override
    public Resource findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException {
        List<Resource> resources = findByCriteria(Restrictions.eq("resourceIdentificationCode", resourceIdentificationCode));
        if (resources != null && !resources.isEmpty()) {
            return resources.get(0);
        } else {
            throw new ItemNotFoundException("Resource not found");
        }
    }

    @Override
    public void update(String resourceId, int processId, String resourceName) throws ItemNotFoundException {
        Resource resource = findByIdentificationCode(resourceId);
        Process process = processRepositoryInterface.findById(processId);
        resource.setProcess(process);
        resource.setResourceName(resourceName);
        saveOrUpdate(resource);
    }
}
