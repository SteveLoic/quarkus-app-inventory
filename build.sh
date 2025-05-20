#!/bin/bash

# Stoppe l’exécution en cas d’erreur
set -e

echo "Compilation Maven en cours..."
mvn clean package -DskipTests

echo "Build et démarrage des containers Docker..."
docker-compose up --build