package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.ConfigurationKey;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sasitha on 2/18/17.
 */
public interface ConfigurationKeyRepositoryInterface extends CrudRepository<ConfigurationKey, Integer> {

    ConfigurationKey findConfigurationKeyByKey(String key);
}
