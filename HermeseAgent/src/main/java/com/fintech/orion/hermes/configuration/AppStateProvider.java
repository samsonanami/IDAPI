package com.fintech.orion.hermes.configuration;

/**
 * Created by TharinduMP on 10/10/2016.
 * AppStateProvider
 */
public class AppStateProvider implements AppStateProviderInterface {
    public static final String ENV_DEBUG = "debug";

    @Override
    public boolean isAgentOnDebugState() {
        return System.getProperty("agentState") != null && ENV_DEBUG.equals(System.getProperty("agentState"));
    }
}
