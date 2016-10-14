package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import org.springframework.stereotype.Service;

@Service
public class ProcessConfigService extends AbstractService<ProcessConfig, Integer> implements ProcessConfigServiceInterface {
}
