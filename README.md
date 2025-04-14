# Application Web de Gestion de réservation de spectacles

## Contexte du Projet

Ce projet a été réalisé dans le cadre du cours Projet d'intégration de Développement du Bachelier en Informatique de Gestion, orientation développement d'applications à l'**ICC**. L'application permet aux utilisateurs de consulter la liste des spectacles, lire leur fiche détaillée, réserver des places et laisser des commentaires.

## Membres du groupe de travail:

- Aktan Stéphane
- Amankour Mohamed
- Bah Elhadj S.
- Dugic Nermina
- Nguyen Duy Anh
- Nibigira Salix
- El Azab Ousama

## Fonctionnalités Principales

- **Inscription et Authetification**  
  Permet aux utilisateurs de créer un compte et de se connecter.
- **Consulter la liste des spectacles et leurs détails**  
  Les utilisateurs peuvent consulter la liste des spectacles et lire leur fiche détaillée.
- **Réservation de places**  
  Les utilisateurs peuvent réserver des places pour les spectacles.
- **Gestion des commentaires**  
  Les utilisateurs peuvent laisser des commentaires sur les spectacles.

## Technologies et Outils Utilisés
- **Backend :**
    - Langage : Java
    - Framework : Spring Boot
    - Environnement de développement : IntelliJ IDEA
- **Base de Données :**
    - MySQL via Wampserver
- **Frontend :**
    - HTML, CSS, Tailwind CSS, vue.js

## Installation et Exécution

### Prérequis
- JDK 11 ou supérieur
- IntelliJ IDEA
- Wampserver (pour MySQL)
- Maven

### Instructions

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/Betteranh/pid-reservation-groupe.git
2. **Ouvrir le projet :**
   Importez le projet dans IntelliJ IDEA.
3. **Configurer la base de données :**
   Modifiez le fichier application.properties (ou application.yml) pour définir les paramètres de connexion à votre instance MySQL.
4. **Lancer l'application :**
   Via IntelliJ IDEA en exécutant la classe principale contenant la méthode main.
   Ou via Maven avec la commande :
   ```bash
   mvn spring-boot:run
5. **Accéder à l'application :**
   Ouvrez http://localhost:8080 dans votre navigateur.

6. **Partie front-end en cour (vue.js)**
   https://github.com/CyberAkr/pid-reservation-grp-front


