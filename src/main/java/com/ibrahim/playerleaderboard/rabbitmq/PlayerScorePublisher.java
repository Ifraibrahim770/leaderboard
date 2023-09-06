package com.ibrahim.playerleaderboard.rabbitmq;

import com.ibrahim.playerleaderboard.enums.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class PlayerScorePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue scoreAddQueue;
    private final Queue scoreUpdateQueue;


    public boolean sendMessage(Object message) {
        try {
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("scoreAddQueue", message, correlationData);
            return true;
        } catch (AmqpException e) {
            return false;
        }
    }


}
