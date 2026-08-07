FROM eclipse-temurin:25-jre
LABEL authors="kiwi"

WORKDIR /app

COPY target/wit-*.jar app.jar

EXPOSE 8080
EXPOSE 5433

ENTRYPOINT ["java", "-jar", "app.jar"]