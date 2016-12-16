package com.fintech.orion.rest;

import com.fintech.orion.coreservices.ProcessConfigServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.configs.Config;
import com.fintech.orion.dataabstraction.models.configs.ConfigResults;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import com.fintech.orion.common.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConfigController {

    private static final String TAG = "ItemController: ";
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ProcessConfigServiceInterface processConfigServiceInterface;

    @RequestMapping(value = "v1/configs/{processType}", method = RequestMethod.GET)
    @ResponseBody
    public Object processConfigs(@PathVariable int processType,
                                 HttpServletResponse response,
                                 @RequestParam("access_token") String accessToken) throws ItemNotFoundException {
        try {
            List<ProcessConfigDTO> processConfigs = processConfigServiceInterface.findById(processType);
            ConfigResults results = new ConfigResults();
            results.setProcessType(String.valueOf(processType));
            List<Config> configs = new ArrayList<>();
            for(ProcessConfigDTO p : processConfigs){
                Config config = new Config();
                config.setKey(p.getKey());
                config.setValue(p.getValue());
                configs.add(config);
            }
            results.setConfigs(configs);
            return results;
        } catch (Exception ex){
            LOGGER.error(TAG, ex);
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }

    }
}
