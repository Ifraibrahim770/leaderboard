package com.ibrahim.playerleaderboard.repositories;

import com.ibrahim.playerleaderboard.entities.PlayerScore;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerScoreRepository extends JpaRepository<PlayerScore,Long> {


    @Cacheable("topPlayers")
    @Query("SELECT p FROM PlayerScore p ORDER BY p.score DESC LIMIT :N")
    List<PlayerScore> getTopPlayers(@Param("N") int n);

    @Query("SELECT COUNT(p) + 1 FROM PlayerScore p WHERE p.score > :playerScore")
    int getPlayerRank(@Param("playerScore") int playerScore);

}
