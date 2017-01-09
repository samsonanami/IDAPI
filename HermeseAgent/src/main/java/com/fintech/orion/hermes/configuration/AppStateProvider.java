package com.fintech.orion.hermes.configuration;

/**
 * Created by TharinduMP on 10/10/2016.
 * AppStateProvider
 */
public class AppStateProvider implements AppStateProviderInterface {
    public static final String ENV_FILE = "file";

    @Override
    public boolean isContextFileLoadingFromFilePath() {
        return System.getProperty("applicationContextFrom") != null &&
                ENV_FILE.equals(System.getProperty("applicationContextFrom"));
    }
}
