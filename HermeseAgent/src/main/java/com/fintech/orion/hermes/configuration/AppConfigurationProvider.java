package com.fintech.orion.hermes.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by TharinduMP on 10/10/2016.
 */
public class AppConfigurationProvider implements AppConfigurationProviderInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfigurationProvider.class);
    private static final String SPRING_CONFIG_NAME = "applicationContext.xml";

    private AppStateProviderInterface appStateProvider;

    public AppConfigurationProvider(AppStateProviderInterface appStateProvider) {
        this.appStateProvider = appStateProvider;
    }

    @Override
    public AbstractApplicationContext getAppContext() {
        AbstractApplicationContext applicationContext;

        if (appStateProvider.isAgentOnDebugState()) {
            LOGGER.info("Since agent state is provided as debug, spring application context is taken from classpath");
            applicationContext = new ClassPathXmlApplicationContext("classpath:" + SPRING_CONFIG_NAME);
        } else {
            LOGGER.info("Since agent state is not provided as debug, spring application context is taken from file system");
            applicationContext = new FileSystemXmlApplicationContext("file:" + SPRING_CONFIG_NAME);
        }
        return applicationContext;
    }
}
