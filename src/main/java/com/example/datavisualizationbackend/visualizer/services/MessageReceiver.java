package com.example.datavisualizationbackend.visualizer.services;

import com.example.datavisualizationbackend.shared.models.StoredEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitHandler
    public void receiveMessage(byte[] in) {
        Gson gson = new GsonBuilder().create();
        StoredEvent event = gson.fromJson(new String(in), StoredEvent.class);
        logger.info("--POSTED THE EVENT: " + event.toString() + "--");
    }

}




