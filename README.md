# leaderboard


## To deploy the project (Locally):<br>
 - Install Docker (get it from [HERE](https://docs.docker.com/get-docker/):  and make sure its added to the PATH
 - Navigate to the project's root directory
 - Open a terminal session using Windows Terminal, GitBash or any other CLI tool 
 - Run the command:<br>
   ```
   docker-compose build
   ```
  -  Then once the image has finished building

   ```
   docker-compose up
   ```
   
 - Wait for the image to build and run on port 8080

## Project Endpoints:<br>
  ```
 POST http://127.0.0.1:8080/players
 ```
   For updating players, accepts a player object
  ```
 PUT http://127.0.0.1:8080/players{id}/score
   ```
   Accepts a score and updates it to the player specified on the id
   ```
 GET http://127.0.0.1:8080/leaderboard/top/{n}
   ```
   Returns the top n players based on score
   
   ```
  GET ttp://127.0.0.1:8080/players/{id}/rank
   ```
  Returns the rank of the specified player


