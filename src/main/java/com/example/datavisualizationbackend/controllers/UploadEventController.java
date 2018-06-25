package com.example.datavisualizationbackend.controllers;

import com.example.datavisualizationbackend.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class UploadEventController {

    @Autowired
    private MessageProducer messageProducer;


    @GetMapping
    public String produce(@RequestParam String message) {

        messageProducer.sendMessage(message);
        return "Message Sent to rabbitMQ and message=" + message;
    }

}
