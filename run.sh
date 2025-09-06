#!/bin/bash

# Variables
IMAGE_NAME=translation-service
CONTAINER_NAME=translation-service-container
PORT=8080

echo "🧹 Removing old container (if exists)..."
docker rm -f $CONTAINER_NAME 2>/dev/null || true

echo "🚀 Running Docker container from existing image..."
docker run -d \
  --name $CONTAINER_NAME \
  -p $PORT:8080 \
  $IMAGE_NAME

echo "✅ Application is running at: $PORT"

