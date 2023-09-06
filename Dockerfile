# Use a base image with Java and Maven pre-installed
FROM openjdk:11-jre-slim

ENV SPRING_RABBITMQ_HOST=localhost
ENV SPRING_RABBITMQ_PORT=5672
ENV SPRING_RABBITMQ_USERNAME=guest
ENV SPRING_RABBITMQ_PASSWORD=guest
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/player_score
ENV SPRING_DATASOURCE_USERNAME=mysqluser
ENV SPRING_DATASOURCE_PASSWORD=ThePassword
ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME=com.mysql.cj.jdbc.Driver
ENV REDIS_HOST=localhost
ENV REDIS_PORT=6379
ENV REDIS_PASSWORD=
ENV DEFAULT_TTL=180

# AS <NAME> to name this stage as maven
FROM maven:3.8.4 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=player-leader-board-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app


COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","player-leader-board-0.0.1-SNAPSHOT.jar"]






