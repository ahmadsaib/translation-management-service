#!/bin/bash

set -e

APP_NAME="translation-service"
JAR_NAME="translation-service-1.0.0-SNAPSHOT.jar"
IMAGE_NAME="translation-service:latest"

echo "🧼 Cleaning and building the project with Maven..."
mvn clean package -DskipTests

echo "📦 Building Docker image: $IMAGE_NAME"
docker build -t $IMAGE_NAME .

echo "✅ Build complete. Docker image '$IMAGE_NAME' is ready."
