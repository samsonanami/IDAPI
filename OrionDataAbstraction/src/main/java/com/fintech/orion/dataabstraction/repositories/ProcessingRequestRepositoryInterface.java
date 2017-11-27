package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProcessingRequestRepositoryInterface extends JpaRepository<ProcessingRequest, Integer> {
    ProcessingRequest findProcessingRequestByProcessingRequestIdentificationCode(String identificationCode);

    ProcessingRequest findProcessingRequestByProcessingRequestIdentificationCodeAndClient(String identificationCode, Client client);

    Page<ProcessingRequest> findProcessingRequestByClient(Client client, Pageable pageable);

    @Query("select pr from ProcessingRequest pr where pr.finalVerificationStatus in ?1 and pr.receivedOn >= ?2 and pr.processingCompletedOn <= ?3 and pr.clientName= ?4 and pr.client in ?5")
    Page<ProcessingRequest> filterProcessingRequestByFilteringAll(List<ProcessingStatus> status, Date from, Date to,
            String clientName, Pageable pageable,List<Client> clients);

    @Query("select pr from ProcessingRequest pr where  pr.receivedOn >= ?1 and pr.processingCompletedOn <= ?2 and pr.client in ?3")
    Page<ProcessingRequest> filterProcessingRequestByFilteringFromAndTo(Date from, Date to, Pageable pageable,List<Client> clients);

    @Query("select pr from ProcessingRequest pr where  pr.finalVerificationStatus in ?1 and pr.clientName= ?2 and pr.client in ?3")
    Page<ProcessingRequest> filterProcessingRequestByFilteringFrom(List<ProcessingStatus> statusId, String clientName,
            Pageable pageable,List<Client> clients);

    @Query("select pr from ProcessingRequest pr where  pr.receivedOn >= ?1 and pr.processingCompletedOn <= ?2 and pr.clientName= ?3 and pr.client in ?4")
    Page<ProcessingRequest> filterProcessingRequestByFilteringClient(Date from, Date to, String clientName,
            Pageable pageable,List<Client> clients);

    @Query("select pr from ProcessingRequest pr where pr.finalVerificationStatus in ?1 and pr.receivedOn >= ?2 and pr.processingCompletedOn <= ?3 and pr.client in ?4")
    Page<ProcessingRequest> filterProcessingRequestByFilteringStatus(List<ProcessingStatus> statusId, Date from,
            Date to, Pageable pageable,List<Client> clients);

    @Query("select pr from ProcessingRequest pr where  pr.clientName >= ?1 and pr.client in ?2 ")
    Page<ProcessingRequest> filterProcessingRequestByFilteringclientName(String clientName, Pageable pageable,List<Client> clients);

}
