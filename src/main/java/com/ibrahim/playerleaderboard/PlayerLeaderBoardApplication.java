package com.ibrahim.playerleaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PlayerLeaderBoardApplication {



	public static void main(String[] args) {

		SpringApplication.run(PlayerLeaderBoardApplication.class, args);
	}

}
