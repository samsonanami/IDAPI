package com.fintech.orion.dataabstraction.models.configs;

import java.util.List;

public class ConfigResults {

    private String processType;
    private List<Config> configs;

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }
}
