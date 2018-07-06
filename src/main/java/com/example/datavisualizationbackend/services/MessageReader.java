package com.example.datavisualizationbackend.services;

import com.example.datavisualizationbackend.conifg.RabbitMQConfiguration;
import com.example.datavisualizationbackend.models.Event;
import com.example.datavisualizationbackend.models.EventHack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ErrorHandler;

import java.util.Date;


public class MessageReader {

    static public void ReadMessages() {
        System.out.println("READING MESSAGES");
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




