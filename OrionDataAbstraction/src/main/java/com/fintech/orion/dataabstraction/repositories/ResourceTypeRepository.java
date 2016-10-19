package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceTypeRepository extends AbstractDAO<ResourceType, Integer> implements ResourceTypeRepositoryInterface {

    public static final String TYPE = "type";

    protected ResourceTypeRepository() {
        super(ResourceType.class);
    }

    @Override
    public ResourceType findByType(String type) throws ItemNotFoundException {
        List<ResourceType> resourceTypes = findByCriteria(Restrictions.eq(TYPE, type));
        if (resourceTypes != null && !resourceTypes.isEmpty()) {
            return resourceTypes.get(0);
        } else {
            throw new ItemNotFoundException("ResourceType not found");
        }
    }
}
