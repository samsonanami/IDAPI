package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.ConnectionHandlerException;
import com.fintech.orion.messaging.connection.ConnectionHandlerInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/7/2016.
 */
public class JobProducer implements JobProducerInterface {

    @Autowired
    private ConnectionHandlerInterface connectionHandler;

    private ConnectionFactory connectionFactory;
    private String queueName;
    private Session session;


    public JobProducer(ConnectionFactory connectionFactory, String queueName) {
        this.connectionFactory = connectionFactory;
        this.queueName = queueName;
        establishSession();
    }

    @Override
    public void produceJob() {

    }

    private void establishSession(){
        try {
            this.session = connectionHandler.establishSession();
        } catch (ConnectionHandlerException e) {
            e.printStackTrace();
        }
    }

}
