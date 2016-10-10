package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessingRequestRepository extends AbstractDAO<ProcessingRequest, Integer> implements ProcessingRequestRepositoryInterface {

    public static final String PROCESSING_REQUEST_IDENTIFICATION_CODE = "processingRequestIdentificationCode";

    protected ProcessingRequestRepository() {
        super(ProcessingRequest.class);
    }

    @Override
    public ProcessingRequest getProcessingRequestByIdentificationCode(String identificationCode) throws ItemNotFoundException {
        List<ProcessingRequest> processingRequests = findByCriteria(Restrictions.eq(PROCESSING_REQUEST_IDENTIFICATION_CODE, identificationCode));
        if (processingRequests != null && !processingRequests.isEmpty()) {
            return processingRequests.get(0);
        } else {
            throw new ItemNotFoundException("Client not found");
        }
    }
}
