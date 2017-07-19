package com.fintech.orion.hermes.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Created by sasitha on 2/18/17.
 */
public class HermeseAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseAsyncUncaughtExceptionHandler.class);
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        LOGGER.error("Uncaught exception occured in async method {} for params {} ", method, params, ex);
    }
}
