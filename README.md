# Publicnex Project

## Overview
This project contains two Spring Boot microservices:

- **Order Service (`spring-publicnext`)**
  - Manages customer orders (PostgreSQL)
  - Processes unprocessed orders every minute and sends logs to the Logging Service

- **Logging Service (`spring-logging-service`)**
  - Stores logs of processed orders (MongoDB)

A Python script (`main.py`) is included to send POST requests for testing.

---

## Prerequisites
- Java 17
- Maven (for building JARs)
- Docker & Docker Compose
- Python 3 (for testing script)

---

Start all services

docker-compose -f docker-compose.yml up -d --build

Stop all services

docker-compose -f docker-compose.yml down

## Access Services

Order Service: http://localhost:8080

Logging Service: http://localhost:8081

PostgreSQL: localhost:5432 (DB: public_db, User: public_user)

MongoDB: localhost:27017 (User: public_user)

⚠️ Warning: These credentials are for local development only. Do not use them in production.
