package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessRepository extends AbstractDAO<Process, Integer> implements ProcessRepositoryInterface {

    public static final String PROCESS_IDENTIFICATION_CODE = "processIdentificationCode";

    protected ProcessRepository() {
        super(Process.class);
    }

    @Override
    public Process findByIdentificationCode(String identificationCode) throws ItemNotFoundException {
        List<Process> processes = findByCriteria(Restrictions.eq(PROCESS_IDENTIFICATION_CODE, identificationCode));
        if (processes != null && !processes.isEmpty()) {
            return processes.get(0);
        } else {
            throw new ItemNotFoundException("Process not found");
        }
    }
}
