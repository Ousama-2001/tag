FROM openjdk:21
LABEL authors="tnguy"

# Copier le JAR généré dans le conteneur
COPY target/reservationsspringboot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
