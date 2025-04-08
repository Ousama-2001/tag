FROM openjdk:21-slim
LABEL authors="tnguy"

# Argument pour le chemin du fichier JAR généré
ARG JAR_FILE=target/reservationsspringboot-0.0.1-SNAPSHOT.jar

# Copier le JAR dans l'image et le renommer pour plus de simplicité
COPY ${JAR_FILE} app.jar

# Exposer le port 8080 (à adapter si votre application écoute sur un autre port)
EXPOSE 8080

# Lancer l'application en exécutant le fichier JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
