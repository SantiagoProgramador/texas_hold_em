FROM maven:3.8.4-openjdk-11 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean install

FROM openjdk:11-jre-slim

EXPOSE 8080 

COPY --from=build /app/target/texas_holdem-0.0.1-SNAPSHOT.jar /app/texas_holdem-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/texas_holdem-0.0.1-SNAPSHOT.jar"]
