package com.fintech.orion.hermesagentservices.provider;

import com.fintech.orion.common.exceptions.ConfigurationProvidorException;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class ConfigurationProvider implements ConfigurationProviderInterface{

    @Autowired
    private ProcessConfigRepositoryInterface processConfigRepositoryInterface;

    @Autowired
    private ConfigurationKeyRepositoryInterface configurationKeyRepositoryInterface;

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;


    @Override
    @Transactional
    public String getConfigurationValue(String clientLicenseKey, String configurationKey, String processType) throws ConfigurationProvidorException {
        String configValue ="";
        try {
            License license = licenseRepositoryInterface.findLicenseByLicenseKey(clientLicenseKey);
            Client client = clientRepositoryInterface.findClientByLicenses(license);
            ConfigurationKey key = configurationKeyRepositoryInterface.findConfigurationKeyByKey(configurationKey);
            ProcessType process = processTypeRepositoryInterface.findProcessTypeByType(processType);
            ProcessConfig processConfig = processConfigRepositoryInterface
                    .findProcessConfigByConfigurationKeyAndProcessTypeAndClient(key, process, client);

            if(processConfig != null){
                configValue = processConfig.getValue();
            }else {
                processConfig = processConfigRepositoryInterface
                        .findProcessConfigByConfigurationKeyAndProcessType(key, process);
                if (processConfig != null){
                    configValue = processConfig.getValue();
                }else {
                    throw new ConfigurationProvidorException("Unable to get the default configuration value for " +
                            "key " + configurationKey + " for process type " + processType);
                }
            }

        } catch (ItemNotFoundException e) {
            throw new ConfigurationProvidorException("Unable to find the configuration ", e);
        }
        return configValue;
    }
}
