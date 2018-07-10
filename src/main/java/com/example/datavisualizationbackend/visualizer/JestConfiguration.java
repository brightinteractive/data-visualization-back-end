package com.example.datavisualizationbackend.visualizer;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class JestConfiguration {

    @Value("${searchbox.url:http://localhost:9200}")
    private String connectionUrl;

    @Bean
    public JestClient jestClient() throws Exception{

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(connectionUrl)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }
}