FROM maven:3.9.11-eclipse-temurin-25 AS build
LABEL authors="kiwi"

WORKDIR /app

COPY pom.xml .
COPY src src
RUN mvn clean package

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/target/wit-*.jar app.jar

EXPOSE 8080
EXPOSE 5433

ENTRYPOINT ["java", "-jar", "app.jar"]