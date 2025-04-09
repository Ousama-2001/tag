# Étape 1 : Construction avec Eclipse Temurin JDK21 + installation de Maven
FROM eclipse-temurin:21 AS builder

# Installer Maven (basé sur Debian/Distro utilisée par l'image)
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copier pom.xml et les sources
COPY pom.xml .
COPY src ./src

# Pour diagnostiquer si besoin, vous pouvez lister les fichiers copiés
# RUN ls -la && ls -la src

# Compiler l'application et générer le JAR
RUN mvn clean package -DskipTests

# Étape 2 : Image finale avec JDK 21
FROM eclipse-temurin:21
WORKDIR /app

# Copier le jar généré depuis l'étape builder
COPY --from=builder /app/target/reservationsspringboot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
