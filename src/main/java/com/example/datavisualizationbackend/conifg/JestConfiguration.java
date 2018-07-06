package com.example.datavisualizationbackend.conifg;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JestConfiguration {

    @Bean
    public JestClient jestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(System.getenv("SEARCHBOX_SSL_URL"))
                .multiThreaded(true)
                .build());
        JestClient client = factory.getObject();
        return client;
    }
}