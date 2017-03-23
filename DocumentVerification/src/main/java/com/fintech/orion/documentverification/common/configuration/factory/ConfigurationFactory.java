package com.fintech.orion.documentverification.common.configuration.factory;

import com.fintech.orion.documentverification.common.configuration.DocumentMrzDecodingConfigurations;

/**
 * Created by TharinduMP on 3/21/2017.
 */
@FunctionalInterface
public interface ConfigurationFactory {
    DocumentMrzDecodingConfigurations getConfiguration(String templateCategory);
}
