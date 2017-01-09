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

        if (appStateProvider.isContextFileLoadingFromFilePath()) {
            LOGGER.info("Loading application context from file {} from file path {}",
                    SPRING_CONFIG_NAME, System.getProperty("contextFilePath"));
            applicationContext = new FileSystemXmlApplicationContext("file:" +
                    System.getProperty("contextFilePath") +"/" +SPRING_CONFIG_NAME);
        } else {
            LOGGER.info("Loading application context from file {} in classpath. This is the default option. " +
                    "If you want to load it " +
                    "from a specific file location it can be do so by setting up env variable " +
                    "-DapplicationContextFrom=\"file\" and -DcontextFilePath=\"<<your file location without " +
                    "file name >>\"", SPRING_CONFIG_NAME );
            applicationContext = new ClassPathXmlApplicationContext("classpath:" + SPRING_CONFIG_NAME);
        }
        return applicationContext;
    }
}
