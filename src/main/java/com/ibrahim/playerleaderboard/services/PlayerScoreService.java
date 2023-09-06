package com.ibrahim.playerleaderboard.services;

import com.ibrahim.playerleaderboard.entities.PlayerScore;
import com.ibrahim.playerleaderboard.models.BaseResponse;
import com.ibrahim.playerleaderboard.rabbitmq.PlayerScorePublisher;
import com.ibrahim.playerleaderboard.repositories.PlayerScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerScoreService {


    @Autowired
    private PlayerScoreRepository playerScoreRepository;

    @Autowired
    private PlayerScorePublisher playerScorePublisher;


    public BaseResponse addPlayerScore(PlayerScore playerScore){
       boolean isPlayerPublished = playerScorePublisher.sendMessage(playerScore);
       if (isPlayerPublished){
           return new BaseResponse(true, "Successfully added players");
       }
       return new BaseResponse(false, "An error has occurred");

    }

    public BaseResponse updatePlayerScore(Integer score, Long playerId){

        PlayerScore playerToUpdate = playerScoreRepository.findById(playerId).orElse(null);

        if(playerToUpdate == null){
            return new BaseResponse(false, "Player with specified id does not exist");
        }
        //update the score
        playerToUpdate.setScore(score);

        boolean isPlayerPublished = playerScorePublisher.sendMessage(playerToUpdate);
        if (isPlayerPublished){
            return new BaseResponse(true, "Successfully updated players score");
        }
        return new BaseResponse(false, "An error has occurred");

    }

    public List<PlayerScore> getRankedPlayers(int n){
        return playerScoreRepository.getTopPlayers(n);
    }

    public int getPlayerRank(long playerId) {
        PlayerScore playerScore = playerScoreRepository.findById(playerId).orElse(null);
        if (playerScore == null) {
            return -1;
        }
        int playerScoreValue = playerScore.getScore();
        return playerScoreRepository.getPlayerRank(playerScoreValue);
    }


}
