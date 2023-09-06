package com.ibrahim.playerleaderboard.controllers;

import com.ibrahim.playerleaderboard.controllers.ScoreController;
import com.ibrahim.playerleaderboard.entities.PlayerScore;
import com.ibrahim.playerleaderboard.models.BaseResponse;
import com.ibrahim.playerleaderboard.services.PlayerScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ScoreControllerTest {

    @InjectMocks
    private ScoreController scoreController;

    @Mock
    private PlayerScoreService playerScoreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPlayerScore() {
        PlayerScore newPlayer = new PlayerScore(); // Create a player to add
        when(playerScoreService.addPlayerScore(any(PlayerScore.class)))
                .thenReturn(new BaseResponse(true, "Player added successfully"));

        BaseResponse response = scoreController.addPlayerScore(newPlayer);

        assertTrue(response.isSuccess());
        assertEquals("Player added successfully", response.getMessage());
    }

    @Test
    public void testUpdatePlayerScore() {
        Long playerId = 1L;
        Map<String, Object> playerScoreMap = new HashMap<>();
        playerScoreMap.put("score", 100);

        when(playerScoreService.updatePlayerScore(any(Integer.class), any(Long.class)))
                .thenReturn(new BaseResponse(true, "Score updated successfully"));

        BaseResponse response = scoreController.updatePlayerScore(playerId, playerScoreMap);

        assertTrue(response.isSuccess());
        assertEquals("Score updated successfully", response.getMessage());
    }

    @Test
    public void testGetRankedPlayers() {
        int n = 5;
        List<PlayerScore> rankedPlayers = new ArrayList<>();
        // Populate rankedPlayers list with test data

        when(playerScoreService.getRankedPlayers(anyInt())).thenReturn(rankedPlayers);

        List<PlayerScore> result = scoreController.getRankedPlayers(n);

        assertEquals(rankedPlayers, result);
    }

    @Test
    public void testGetPlayerRank() {
        long playerID = 1L;

        when(playerScoreService.getPlayerRank(anyLong())).thenReturn(1);

        int rank = scoreController.getPlayerRank(playerID);

        assertEquals(1, rank);
    }
}

