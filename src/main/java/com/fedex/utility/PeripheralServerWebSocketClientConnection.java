package com.fedex.utility;

import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - OTP Device virtualization<br>
 * Description - This class is utility class of Communication Layer which connects with peripheral server through
 * WebSocket
 * 
 * @author Abhishek Singhal [3692173]
 * @version 1.0.0
 * @since Feb 13, 2020
 */
public class PeripheralServerWebSocketClientConnection {

    private static Logger logger = LogManager.getLogger(PeripheralServerWebSocketClientConnection.class);

    private static StompSession stompSession;

    /**
     * private default constructor is made, as this class only contains static method
     */
    private PeripheralServerWebSocketClientConnection() {
    }

    /**
     * This method connects to the WebSocket server using Stomp Client and returns the connection status. 
     * 
     * @param endpoint the WebSocket endpoint
     * @param sessionHandler the implementation of {@link StompSessionHandler}
     * @since Feb 13, 2020
     */
    public static void connectToWebSocketServer(String endpoint, StompSessionHandler sessionHandler) {
        logger.info("Attempting to connect to websocket server: [WS Endpoint : {}]", endpoint);
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());
        try {
            stompSession = stompClient.connect(endpoint, sessionHandler).get();
        } catch (InterruptedException interruptedException) {
            logger.error("Interrupted Exception occurred: [WS Endpoint : {}] with exception: {}", endpoint,
                            ExceptionUtils.getStackTrace(interruptedException));
            Thread.currentThread().interrupt();
        } catch (ExecutionException executionException) {
            logger.error("Execution Exception occurred: [WS Endpoint : {}] with exception: {}", endpoint,
                            ExceptionUtils.getStackTrace(executionException));
        }
    }
    
    /**
     * This method returns the websocket client Stomp Session
     * 
     * @return instance of Stomp Session
     * @since Feb 13, 2020
     */
    public static StompSession getStompSession() {
        return stompSession;
    }
}
