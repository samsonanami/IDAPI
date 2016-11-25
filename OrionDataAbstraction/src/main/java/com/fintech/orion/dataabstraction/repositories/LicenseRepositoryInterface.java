package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface LicenseRepositoryInterface extends DAOInterface<License, Integer> {

    List<License> getCurrentlyActiveLicenseListOfClient(Client client) throws ItemNotFoundException;

    License getLicenseByLicenseKey(String licenseKey) throws ItemNotFoundException;
}
