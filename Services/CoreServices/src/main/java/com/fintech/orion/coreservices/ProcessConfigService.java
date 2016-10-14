package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dto.process.ProcessDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessConfigService extends AbstractService<ProcessConfig, Integer> implements ProcessConfigServiceInterface {
}
