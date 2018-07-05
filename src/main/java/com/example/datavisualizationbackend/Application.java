package com.example.datavisualizationbackend;

import com.example.datavisualizationbackend.services.MessageReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        MessageReader messageReader = new MessageReader();
        messageReader.ReadMessages();
    }

}