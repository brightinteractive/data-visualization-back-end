package com.example.datavisualizationbackend;

import com.example.datavisualizationbackend.services.MessageReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        MessageReader.ReadMessages();
    }

}