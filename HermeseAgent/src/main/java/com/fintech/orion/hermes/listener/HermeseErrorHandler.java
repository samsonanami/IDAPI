package com.fintech.orion.hermes.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * Created by sasitha on 7/13/17.
 *
 */
@Component
public class HermeseErrorHandler implements ErrorHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseErrorHandler.class);
    @Override
    public void handleError(Throwable t) {
        LOGGER.error("Error occured while processing hermese message {} ",t);
    }
}
