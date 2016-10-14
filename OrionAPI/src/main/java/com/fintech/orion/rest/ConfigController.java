package com.fintech.orion.rest;

import com.fintech.orion.coreservices.ProcessConfigServiceInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessConfigRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ConfigController {

    private static final String TAG = "ItemController: ";
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ProcessConfigServiceInterface processConfigServiceInterface;

    @Autowired
    private ProcessConfigRepositoryInterface processConfigRepositoryInterface;

    @RequestMapping(value = "v1/configs", method = RequestMethod.GET)
    @ResponseBody
    public Object processConfigs(@PathVariable int processType,
                                 HttpServletResponse response,
                                 @RequestParam("access_token") String accessToken){
        return null;
    }
}
