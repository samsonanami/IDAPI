package com.fintech.orion.hermes.handler;

import javax.jms.Message;

/**
 * Created by TharinduMP on 10/13/2016.
 */
public interface JobManagerInterface {
    void delegateJob(Message message) throws Exception;
}
