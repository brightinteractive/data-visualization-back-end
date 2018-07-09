package com.example.datavisualizationbackend.simulator.services;

import com.example.datavisualizationbackend.shared.models.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadEventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void simulateUploadEvent(Event event) {
        rabbitTemplate.convertAndSend(event);
    }

}
