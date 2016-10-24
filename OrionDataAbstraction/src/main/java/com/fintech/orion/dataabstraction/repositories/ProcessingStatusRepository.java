package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProcessingStatusRepository extends AbstractDAO<ProcessingStatus, Integer> implements ProcessingStatusRepositoryInterface {

    protected ProcessingStatusRepository() {
        super(ProcessingStatus.class);
    }

    @Override
    public ProcessingStatus findByStatus(Status status) throws ItemNotFoundException {
        List<ProcessingStatus> clients = findByCriteria(Restrictions.eq("status", status.toString()));
        if (clients != null && !clients.isEmpty()) {
            return clients.get(0);
        } else {
            throw new ItemNotFoundException("ProcessingStatus not found");
        }
    }
}
