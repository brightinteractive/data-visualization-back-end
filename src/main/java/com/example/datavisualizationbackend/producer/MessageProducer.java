package com.example.datavisualizationbackend.producer;

import com.example.datavisualizationbackend.conifg.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Is listener returned ::: " + rabbitTemplate.isReturnListener());
    }


}
