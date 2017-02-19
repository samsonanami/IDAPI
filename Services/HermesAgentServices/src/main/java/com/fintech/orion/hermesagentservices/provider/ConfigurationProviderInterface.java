package com.fintech.orion.hermesagentservices.provider;

import com.fintech.orion.common.exceptions.ConfigurationProvidorException;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public interface ConfigurationProviderInterface {

    String getConfigurationValue(String clientLicenseKey, String configurationKey, String processType) throws ConfigurationProvidorException;
}
