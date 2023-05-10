# Dockerfile
FROM openjdk:11
RUN apt-get update
# RUN apt-get upgrade gradle
WORKDIR /usr/src
ADD ./server/src/*.jar app.jar
