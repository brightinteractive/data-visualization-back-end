package com.example.datavisualizationbackend.controllers;

import com.example.datavisualizationbackend.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload-event")
public class UploadEventController {

    @Autowired
    private MessageProducer messageProducer;


    @PostMapping
    public String produce(@RequestBody Object message) {
        System.out.println("Message Received: " + message);
        return "Message Recieved!";
//        messageProducer.sendMessage(message);
//        return "Message Sent to rabbitMQ and message=" + message;
    }


}
