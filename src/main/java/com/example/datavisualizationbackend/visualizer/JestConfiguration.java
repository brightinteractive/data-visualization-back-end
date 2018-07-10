package com.example.datavisualizationbackend.visualizer;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class JestConfiguration {


    @Bean
    public JestClient jestClient() throws Exception{

        String connectionUrl;

        if (System.getenv("SEARCHBOX_URL") != null) {
            connectionUrl = System.getenv("SEARCHBOX_URL");

        }  else {
            connectionUrl = "http://localhost:9200";
        }


        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(connectionUrl)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }
}