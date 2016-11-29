package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class LicenseRepository extends AbstractDAO<License, Integer> implements LicenseRepositoryInterface {

    protected LicenseRepository() {
        super(License.class);
    }


    @Override
    @Transactional
    public List<License> getCurrentlyActiveLicenseListOfClient(Client client) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(License.class);
        criteria.add(Restrictions.eq("client", client));
        criteria.add(Restrictions.le("startDate", new Date()));
        criteria.add(Restrictions.ge("endDate", new Date()));
        return criteria.list();
    }

    @Override
    public License getLicenseByLicenseKey(String licenseKey) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(License.class);
        criteria.add(Restrictions.eq("licenseKey", licenseKey));
        License license = (License)criteria.list().get(0);
        if(license == null){
           throw new ItemNotFoundException("No license were found for the license key : " + licenseKey);
        }
        return license;
    }
}
