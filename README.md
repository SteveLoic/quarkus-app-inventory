# quarkus-inventory-app

Inventory API ist eine REST-basierte Backend-Anwendung zur Verwaltung von Lagerbeständen in Unternehmen. Sie ermöglicht die Erfassung, Überwachung und Optimierung von Beständen mit moderner Java-Technologie auf Basis von Quarkus.

## 🚀 Features

- ✅ Moderne REST-API mit Quarkus RESTEasy Reactive

- 📄 Swagger UI & OpenAPI Dokumentation

- 🐳 Vollständig dockerisiert (App & PostgreSQL via Docker Compose)

- ✅ Validierung mit Hibernate Validator

- 🧪 **Integrationstests mit Testcontainers (echte PostgreSQL-Container im Testlauf)**

Docker & Docker Compose

Maven Build

## ✅ Voraussetzungen

Java 17+

Maven (mind. v3.8) – MUSS lokal installiert sein

Docker & Docker Compose

## 🚀 Schnellstart mit Docker Compose

▶️ Starten der App mit einem Bash-Skript

Führe einfach das Skript aus:

```shell script

./build.sh

```

Dies erledigt:

Maven-Build (mvn package)

Docker Image Build

Start der App + PostgreSQL via docker-compose

⚠️ Hinweis: Maven muss installiert sein, da das Skript den Quarkus-Build lokal ausführt.

## 🌐 Öffne die Web-UI

Sobald die Anwendung läuft, ist die Swagger UI unter folgender Adresse verfügbar:

📎 http://localhost:8080/q/swagger-ui

Dort kannst du alle REST-Endpunkte direkt testen.

## 🧪 Dev-Modus (optional für Entwicklung)

Starte die Anwendung mit Hot Reload im Dev-Modus:

```shell script

./mvnw quarkus:dev

```

Dev UI verfügbar unter: http://localhost:8081/q/dev/

## 🌐 Swagger / OpenAPI

OpenAPI UI (Swagger) verfügbar unter:

🔗 http://localhost:8081/q/swagger-ui
