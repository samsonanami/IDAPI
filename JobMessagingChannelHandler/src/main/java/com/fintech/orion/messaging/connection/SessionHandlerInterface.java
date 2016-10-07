package com.fintech.orion.messaging.connection;

import com.fintech.orion.common.exceptions.SessionHandlerException;

import javax.jms.*;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public interface SessionHandlerInterface {

    Session establishSession() throws SessionHandlerException;
}
