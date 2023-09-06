package com.ibrahim.playerleaderboard.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "PlayersScores")
@Data
public class PlayerScore implements Serializable {
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "player_name", nullable = false, length = 255)
    private String name;
    @Column(name = "other_player_information", columnDefinition = "TEXT")
    private String playerInfo;
    @Column(name = "score")
    private int score;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        lastUpdated = new Date();
    }
}
