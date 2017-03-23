package com.fintech.orion.documentverification.common.configuration.factory;

import com.fintech.orion.documentverification.common.configuration.DocumentMrzDecodingConfigurations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TharinduMP on 3/21/2017.
 */
@Component
public class CommonConfigurationFactory implements ConfigurationFactory {

    @Resource(name = "decodingConfigurationsMap")
    private Map<String,DocumentMrzDecodingConfigurations> decodingConfigurationsMap;

    @Override
    public DocumentMrzDecodingConfigurations getConfiguration(String templateCategory) {
        return decodingConfigurationsMap.get(templateCategory);
    }
}
