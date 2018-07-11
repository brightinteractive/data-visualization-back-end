package com.example.datavisualizationbackend.simulator.services;

import com.example.datavisualizationbackend.shared.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulateEventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SimulateEventService.class);

    public void simulateEvent(Event event) {
        rabbitTemplate.convertAndSend(event);
        logger.info(String.format("Sent event message %s", event));
    }

}
