package com.ibrahim.playerleaderboard.services;

import com.ibrahim.playerleaderboard.entities.PlayerScore;
import com.ibrahim.playerleaderboard.models.BaseResponse;
import com.ibrahim.playerleaderboard.rabbitmq.PlayerScorePublisher;
import com.ibrahim.playerleaderboard.repositories.PlayerScoreRepository;
import com.ibrahim.playerleaderboard.services.PlayerScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerScoreServiceTest {

    @InjectMocks
    private PlayerScoreService playerScoreService;

    @Mock
    private PlayerScoreRepository playerScoreRepository;

    @Mock
    private PlayerScorePublisher playerScorePublisher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPlayerScore() {
        PlayerScore playerScore = new PlayerScore(); // Create a player to add
        when(playerScorePublisher.sendMessage(any(PlayerScore.class)))
                .thenReturn(true);

        BaseResponse response = playerScoreService.addPlayerScore(playerScore);

        assertEquals(true, response.isSuccess());
        assertEquals("Successfully added players", response.getMessage());
    }

    @Test
    public void testUpdatePlayerScore() {
        Long playerId = 1L;
        Integer newScore = 100;
        PlayerScore playerToUpdate = new PlayerScore();
        playerToUpdate.setId(playerId);

        when(playerScoreRepository.findById(anyLong())).thenReturn(Optional.of(playerToUpdate));
        when(playerScorePublisher.sendMessage(any(PlayerScore.class)))
                .thenReturn(true);

        BaseResponse response = playerScoreService.updatePlayerScore(newScore, playerId);

        assertEquals(true, response.isSuccess());
        assertEquals("Successfully updated players score", response.getMessage());
    }

    @Test
    public void testGetRankedPlayers() {
        int n = 5;
        PlayerScore playerScore1 = new PlayerScore();
        playerScore1.setScore(10);
        playerScore1.setName("Ibrahim");
        playerScore1.setId(1);


        PlayerScore playerScore2 = new PlayerScore();
        playerScore2.setScore(10);
        playerScore2.setName("Ibrahim");
        playerScore2.setId(1);

        PlayerScore playerScore3 = new PlayerScore();
        playerScore3.setScore(10);
        playerScore3.setName("Ibrahim");
        playerScore3.setId(1);

        List<PlayerScore> scores = new ArrayList<>();
        scores.add(playerScore1);
        scores.add(playerScore2);
        scores.add(playerScore3);

        when(playerScoreRepository.getTopPlayers(anyInt())).thenReturn(scores);

        List<PlayerScore> result = playerScoreService.getRankedPlayers(n);

        assertEquals(scores, result);
    }

    @Test
    public void testGetPlayerRank() {
        long playerId = 1L;
        PlayerScore playerScore = new PlayerScore();
        playerScore.setId(playerId);

        when(playerScoreRepository.findById(anyLong())).thenReturn(Optional.of(playerScore));
        when(playerScoreRepository.getPlayerRank(anyInt())).thenReturn(1);

        int rank = playerScoreService.getPlayerRank(playerId);

        assertEquals(1, rank);
    }
}

