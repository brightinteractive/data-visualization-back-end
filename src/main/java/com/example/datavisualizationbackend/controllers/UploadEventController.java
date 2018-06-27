package com.example.datavisualizationbackend.controllers;

import com.example.datavisualizationbackend.models.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/upload-event")
public class UploadEventController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Event uploadEvent(@RequestBody Event event) {
        rabbitTemplate.convertAndSend(event);
        System.out.println(event);
        return event;
    }


}
