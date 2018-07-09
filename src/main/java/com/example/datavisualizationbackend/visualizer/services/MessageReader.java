package com.example.datavisualizationbackend.visualizer.services;

import com.example.datavisualizationbackend.Application;
import com.example.datavisualizationbackend.shared.conifg.RabbitMQConfiguration;
import com.example.datavisualizationbackend.shared.models.Event;
import com.example.datavisualizationbackend.shared.models.EventHack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import java.util.Date;

@Service
public class MessageReader {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    //TODO Re-implment MessageReader Class
    static public void ReadMessages() {
        logger.debug("--APPLICATION STARTED--");
        final ApplicationContext rabbitConfig = new AnnotationConfigApplicationContext(RabbitMQConfiguration.class);
        final ConnectionFactory rabbitConnectionFactory = rabbitConfig.getBean(ConnectionFactory.class);
        final Queue rabbitQueue = rabbitConfig.getBean(Queue.class);
        final SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();

        listenerContainer.setConnectionFactory(rabbitConnectionFactory);
        listenerContainer.setQueueNames(rabbitQueue.getName());
        setMessageListener(listenerContainer);
        SetErrorHandler(listenerContainer);
        addShutDownHook(listenerContainer);
        listenerContainer.start();
    }

    private static void setMessageListener(SimpleMessageListenerContainer listenerContainer) {
        listenerContainer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                byte[] body = message.getBody();
                Gson gson = new GsonBuilder().create();
                EventHack event = gson.fromJson(new String(body), EventHack.class);
                Event eventOut = new Event(event.getEventType(), event.getUserId(), event.getUserName(), event.getGroup(), event.getAssetId(), event.getAssetTitle(), new Date(event.getDate()));
            }
        });
    }

    private static void addShutDownHook(SimpleMessageListenerContainer listenerContainer) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                listenerContainer.shutdown();
            }
        });
    }

    private static void SetErrorHandler(SimpleMessageListenerContainer listenerContainer) {
        listenerContainer.setErrorHandler(new ErrorHandler() {
            public void handleError(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}




