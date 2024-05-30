FROM --platform=linux/amd64 maven:3.9.0-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY pom.xml ./
RUN mvn clean package
FROM openjdk:17-slim
WORKDIR /app
COPY target/**.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]