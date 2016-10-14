package com.fintech.orion.hermes.configuration;

import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by TharinduMP on 10/10/2016.
 */
public interface AppConfigurationProviderInterface {
    AbstractApplicationContext getAppContext();
}
