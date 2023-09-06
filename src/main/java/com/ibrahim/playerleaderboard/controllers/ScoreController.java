package com.ibrahim.playerleaderboard.controllers;

import com.ibrahim.playerleaderboard.entities.PlayerScore;
import com.ibrahim.playerleaderboard.models.BaseResponse;
import com.ibrahim.playerleaderboard.services.PlayerScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class ScoreController {

    @Autowired
    private PlayerScoreService playerScoreService;



    @PostMapping("/players")
    public BaseResponse addPlayerScore(@RequestBody PlayerScore newPlayer){
        return playerScoreService.addPlayerScore(newPlayer);
    }

    @PutMapping("/players/{id}/score")
    public BaseResponse updatePlayerScore(@PathVariable Long id, @RequestBody Map<String, Object> playerScore) {

        Integer score = (Integer) playerScore.get("score"); // Use Integer to allow null values

        if (score == null) {
            return new BaseResponse(false, "Error, score is not provided");
        }

        return playerScoreService.updatePlayerScore(score, id);
    }



    @GetMapping("leaderboard/top/{n}")
    public List<PlayerScore> getRankedPlayers(@PathVariable int n){

       return playerScoreService.getRankedPlayers(n);
    }

    @GetMapping("/players/{playerID}/rank")
    public int getPlayerRank(@PathVariable long playerID){

        return playerScoreService.getPlayerRank(playerID);
    }
}
