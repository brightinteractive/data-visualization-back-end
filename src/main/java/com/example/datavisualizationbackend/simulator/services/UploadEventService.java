package com.example.datavisualizationbackend.simulator.services;

import com.example.datavisualizationbackend.simulator.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadEventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UploadEventService.class);

    public void simulateUploadEvent(Event event) {
        rabbitTemplate.convertAndSend(event);
        logger.info("--EVENT UPLOADED--");
    }

}
