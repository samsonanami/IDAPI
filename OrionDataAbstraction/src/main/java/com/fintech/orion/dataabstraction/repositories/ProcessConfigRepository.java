package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessConfigRepository extends AbstractDAO<ProcessConfig, ProcessConfigId> implements ProcessConfigRepositoryInterface {

    protected ProcessConfigRepository() {
        super(ProcessConfig.class);
    }

    @Override
    public List<ProcessConfig> findById(int processType) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(ProcessConfig.class);
        criteria.add(Restrictions.eq("id.processType", processType));
        List<ProcessConfig> processConfigs = criteria.list();
        if (processConfigs != null && !processConfigs.isEmpty()) {
            return processConfigs;
        } else {
            throw new ItemNotFoundException("ProcessConfigs not found");
        }
    }
}
