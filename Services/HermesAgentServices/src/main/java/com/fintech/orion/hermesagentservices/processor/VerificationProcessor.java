package com.fintech.orion.hermesagentservices.processor;


import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public interface VerificationProcessor {

    @Async
    Future<Object> process(Object object);
}
