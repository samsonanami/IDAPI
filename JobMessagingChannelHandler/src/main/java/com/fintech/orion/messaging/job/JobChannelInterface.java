package com.fintech.orion.messaging.job;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public interface JobChannelInterface {
    void sendJob();

    void listenForJobs();
}
