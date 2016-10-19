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

    public static final String RESOURCE_IDENTIFICATION_CODE = "resourceIdentificationCode";

    protected ResourceRepository() {
        super(Resource.class);
    }

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Override
    public Resource findByIdentificationCode(String resourceIdentificationCode) throws ItemNotFoundException {
        List<Resource> resources = findByCriteria(Restrictions.eq(RESOURCE_IDENTIFICATION_CODE, resourceIdentificationCode));
        if (resources != null && !resources.isEmpty()) {
            return resources.get(0);
        } else {
            throw new ItemNotFoundException("Resource not found");
        }
    }

    @Override
    public void saveOrUpdate(String resourceId, int processId) throws ItemNotFoundException {
        Resource resource = findByIdentificationCode(resourceId);
        Process process = processRepositoryInterface.findById(processId);
        resource.setProcess(process);
        saveOrUpdate(resource);
    }
}
