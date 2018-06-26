package com.example.datavisualizationbackend.controllers;

import com.example.datavisualizationbackend.conifg.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.core.Queue;

@RestController
@RequestMapping("/upload-event")
public class UploadEventController {

    @Autowired private RabbitTemplate amqpTemplate;
    @Autowired private Queue rabbitQueue;

    @GetMapping
    public String testProduce(){

        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        String test = "HELLO RABBITMQ";
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        amqpTemplate.convertAndSend(test);
        System.out.println("Sent to RabbitMQ: " + test);
        return "Works";
    }


}
