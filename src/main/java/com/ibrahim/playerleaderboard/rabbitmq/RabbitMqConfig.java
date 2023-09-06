package com.ibrahim.playerleaderboard.rabbitmq;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@EnableRabbit
@Configuration
public class RabbitMqConfig {

    // Define a queue named "myQueue"
    @Bean
    public Queue scoreAddQueue() {
        return new Queue("scoreAddQueue");
    }

}
