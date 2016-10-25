package com.fintech.orion.hermes.service;

import com.fintech.orion.hermes.configuration.AppConfigurationProvider;
import com.fintech.orion.hermes.configuration.AppConfigurationProviderInterface;
import com.fintech.orion.hermes.configuration.AppStateProvider;
import com.fintech.orion.hermes.configuration.AppStateProviderInterface;
import com.fintech.orion.messaging.job.JobConsumerInterface;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by TharinduMP on 10/10/2016.
 * The main service class of Hermes Agent
 */

public class Service implements Daemon {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    private static AbstractApplicationContext applicationContext;

    public static void main(String[] args) {
        // start Agent from main method perspective
        startAgent();
    }

    /**
     * Common starting place for the agent.
     * This is implemented in a way to support debugging from main method and running on windows and linux systems.
     */
    public static void startAgent() {
        loadApplicationContext();
        setJobConsumer();
    }

    /**
     * Get Application Context
     * Provide the following VM Options property to enable debugging state and related variables
     * -DagentState="debug"
     * Note : Gradle removes all the resource files including the application context bean file.
     * Manually add them for debugging.
     */
    public static void loadApplicationContext() {
        AppStateProviderInterface appStateProvider = new AppStateProvider();
        AppConfigurationProviderInterface appConfigurationProvider = new AppConfigurationProvider(appStateProvider);
        applicationContext = appConfigurationProvider.getAppContext();
    }

    /**
     * Set Hermes Job Consumer
     */
    private static void setJobConsumer() {
        LOGGER.info("Setting Job Listener...");
        try {
            JobConsumerInterface jobConsumer = (JobConsumerInterface) applicationContext.getBean("jobConsumer");
            MessageListener jobListener = (MessageListener) applicationContext.getBean("jobListener");
            jobConsumer.setJobConsumer(jobListener);
            LOGGER.info("Setting Job Listener Successful.");
        } catch (JMSException e) {
            LOGGER.error("Setting Job Listener Failed.", e);
        }
    }

    @Override
    public void init(DaemonContext context) throws Exception {
        LOGGER.info("Hermes Agent Initializing....");
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("Hermes Agent Starting...");

        // start Agent from daemon service
        startAgent();
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("Hermes Agent Stopping...");
    }

    @Override
    public void destroy() {
        LOGGER.info("Hermes Agent Destroying Allocated System Resources...");
        try {
            // shutdown all Unirest threads after completion
            LOGGER.info("Shutting Down all Unirest Threads...");
            Unirest.shutdown();
            LOGGER.info("Unirest Threads Shutdown Complete.");

            // shutdown concurrent workers after they are done without interrupting
            LOGGER.info("Shutting Down Executor Service...");
            ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) applicationContext.getBean("taskExecutor");
            taskExecutor.shutdown();
            LOGGER.info("Executor Service Shutdown Complete.");

            LOGGER.info("Closing Application Context...");
            applicationContext.close();
            LOGGER.info("Application context Closed.");

        } catch (IOException e) {
            LOGGER.error("Exception in Resource Destroying at Agent shutdown.", e);
        }


    }
}
