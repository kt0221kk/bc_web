# Dockerfile
FROM openjdk:11

WORKDIR /app

COPY . /app

RUN ./gradlew clean build

EXPOSE 8080

CMD ["java", "-jar", "/app/build/libs/your-java-app.jar"]
