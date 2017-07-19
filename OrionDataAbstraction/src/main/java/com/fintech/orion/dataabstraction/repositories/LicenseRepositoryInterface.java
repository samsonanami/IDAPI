package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LicenseRepositoryInterface extends CrudRepository<License, Integer> {


    @Query("select l from License l where l.client  = ?1 and l.startDate < ?2 and l.endDate > ?2 and l.enabled = true and l.status = true ")
    List<License> findLicensesByClientAndLessThanEndDateAndGreaterThanStartDate(Client client, Date today) throws ItemNotFoundException;

    License findLicenseByLicenseKey(String licenseKey) throws ItemNotFoundException;
}
