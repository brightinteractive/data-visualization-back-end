package com.example.datavisualizationbackend.conifg;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.System.getenv;


@Configuration
public class RabbitMQConfig {

    protected final String uploadEventQueueName = "upload.event.queue";

    @Value("amqp://virijqsl:Q4naR8xj2Uy3ybk0kCfYFP3Y0sXBKESk@wolverine.rmq.cloudamqp.com/virijqsl")
    private String cloudAmqpUrl;

    @Bean
    public ConnectionFactory connectionFactory() throws URISyntaxException {
        return new CachingConnectionFactory(new URI(cloudAmqpUrl));
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws URISyntaxException {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws URISyntaxException {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setRoutingKey(this.uploadEventQueueName);
        template.setQueue(this.uploadEventQueueName);
        return template;
    }

    @Bean
    public Queue queue() {
        return new Queue(this.uploadEventQueueName);
    }

    private static String getEnvOrThrow(String name) {
        final String env = getenv(name);
        if (env == null) {
            throw new IllegalStateException("Environment variable [" + name + "] is not set.");
        }
        return env;
    }

}
