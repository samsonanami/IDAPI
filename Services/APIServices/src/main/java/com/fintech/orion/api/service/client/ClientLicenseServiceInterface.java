package com.fintech.orion.api.service.client;

import com.fintech.orion.api.service.exceptions.ClientServiceException;

/**
 *
 * Created by sasitha on 11/3/16.
 */
public interface ClientLicenseServiceInterface {

    String getActiveLicenseOfClient(String clientName) throws ClientServiceException;
}
