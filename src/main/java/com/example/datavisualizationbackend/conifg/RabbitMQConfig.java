package com.example.datavisualizationbackend.conifg;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.System.getenv;


@Configuration
public class RabbitMQConfig {

    protected final String helloWorldQueueName = "hello.world.queue";

    @Bean
    public ConnectionFactory connectionFactory() {
        final URI ampqUrl;
        try {
            ampqUrl = new URI(getEnvOrThrow("CLOUDAMQP_URL"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername(ampqUrl.getUserInfo().split(":")[0]);
        factory.setPassword(ampqUrl.getUserInfo().split(":")[1]);
        factory.setHost(ampqUrl.getHost());
        factory.setPort(ampqUrl.getPort());
        factory.setVirtualHost(ampqUrl.getPath().substring(1));

        return factory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(this.helloWorldQueueName);
        template.setQueue(this.helloWorldQueueName);
        return template;
    }

    @Bean
    public Queue queue() {
        return new Queue(this.helloWorldQueueName);
    }

    private static String getEnvOrThrow(String name) {
        final String env = getenv(name);
        if (env == null) {
            throw new IllegalStateException("Environment variable [" + name + "] is not set.");
        }
        return env;
    }

//    public static final String ROUTING_KEY = "my.queue.key";
//
//    @Bean
//    Queue queue() {
//        return new Queue(ROUTING_KEY, true);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("my_queue_exchange");
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//    }

}
