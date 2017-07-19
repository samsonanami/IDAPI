package com.fintech.orion.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/v1/docs", method = RequestMethod.GET)
    public String index() {
        LOGGER.debug("Redirecting to swagger UI");
        return "redirect:swagger-ui.html";
    }
}
