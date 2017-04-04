package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProcessRepositoryInterface extends CrudRepository<Process, Integer> {

    Process findProcessByProcessIdentificationCode(String identificationCode) throws ItemNotFoundException;

    @Transactional
    @Query("select p from Process p where p.processingRequest = (select pr from ProcessingRequest pr where " +
            "pr.processingRequestIdentificationCode = ?1) and p.processType = (select pt from ProcessType pt where " +
            "pt.type = ?2)")
    Process findProcessByProcessingRequestAndProcessType(String processingRequestCode, String processType);

    @Transactional
    @Query("select p from Process p where p.processingRequest = (select pr from ProcessingRequest pr where " +
            "pr.processingRequestIdentificationCode = ?1)")
    List<Process> findProcessByProcessingRequest(String processingRequestCode);

    @Transactional
    @Query("select p.processType from Process p where  p.processIdentificationCode = ?1")
    ProcessType test(String string);

    @Transactional
    List<Process> findProcessByProcessingRequestAndProcessTypeIn(ProcessingRequest processingRequest, List<ProcessType>
                                                                           processTypeList);

}
