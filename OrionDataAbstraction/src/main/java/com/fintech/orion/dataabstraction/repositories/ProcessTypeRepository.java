package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessTypeRepository extends AbstractDAO<ProcessType, Integer> implements ProcessTypeRepositoryInterface {

    public static final String PROCESS_TYPE = "type";

    protected ProcessTypeRepository() {
        super(ProcessType.class);
    }

    @Override
    public ProcessType getProcessTypeByName(String type) throws ItemNotFoundException {
        List<ProcessType> processTypes = findByCriteria(Restrictions.eq(PROCESS_TYPE, type));
        if (processTypes != null && !processTypes.isEmpty()) {
            return processTypes.get(0);
        } else {
            throw new ItemNotFoundException("ProcessTypes not found");
        }
    }
}
