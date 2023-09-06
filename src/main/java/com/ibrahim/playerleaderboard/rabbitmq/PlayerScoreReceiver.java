package com.ibrahim.playerleaderboard.rabbitmq;

import com.ibrahim.playerleaderboard.entities.PlayerScore;
import com.ibrahim.playerleaderboard.repositories.PlayerScoreRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;


@Component
public class PlayerScoreReceiver {

    @Autowired
    private PlayerScoreRepository playerScoreRepository;

    @RabbitListener(queues = "scoreAddQueue")
    public void addScore(PlayerScore message) {
        System.out.println("Received <" + message + ">");
        playerScoreRepository.save(message);

    }


}
