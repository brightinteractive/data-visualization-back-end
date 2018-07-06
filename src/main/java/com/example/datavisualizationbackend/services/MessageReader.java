package com.example.datavisualizationbackend.services;

import com.example.datavisualizationbackend.conifg.RabbitMQConfig;
import com.example.datavisualizationbackend.models.Event;
import com.example.datavisualizationbackend.models.EventHack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ErrorHandler;

import java.util.Date;


public class MessageReader {

    static public void ReadMessages(){

        final ApplicationContext rabbitConfig = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        final ConnectionFactory rabbitConnectionFactory = rabbitConfig.getBean(ConnectionFactory.class);
        final Queue rabbitQueue = rabbitConfig.getBean(Queue.class);
        final MessageConverter messageConverter = new SimpleMessageConverter();

        final SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(rabbitConnectionFactory);
        listenerContainer.setQueueNames(rabbitQueue.getName());

        listenerContainer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                byte[] body = message.getBody();
                Gson gson = new GsonBuilder().create();
                EventHack event = gson.fromJson(new String(body), EventHack.class);
                Event eventOut = new Event(event.getEventType(), event.getUserId(), event.getUserName(), event.getGroup(), event.getAssetId(), event.getAssetTitle(), new Date(event.getDate()));
                //SEND TO DATABASE
            }
        });

        listenerContainer.setErrorHandler(new ErrorHandler() {
            public void handleError(Throwable t) {
                t.printStackTrace();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                listenerContainer.shutdown();
            }
        });
        listenerContainer.start();
    }
}




