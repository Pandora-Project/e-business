#!/bin/bash

# Configuration
DOCKER_IMAGE="scala-all" # Replace with the name of your Docker image
DOCKER_CONTAINER_NAME="my-scala-app"
APP_PORT=9000

# Check if a container with the same name exists, and stop and remove it if it does
if docker ps -aq -f name="$DOCKER_CONTAINER_NAME" | grep -q .; then
  echo "Stopping and removing existing Docker container named: $DOCKER_CONTAINER_NAME"
  docker stop "$DOCKER_CONTAINER_NAME"
  docker rm "$DOCKER_CONTAINER_NAME"
fi

# Run the Docker container
echo "Running Docker container named: $DOCKER_CONTAINER_NAME from image: $DOCKER_IMAGE"
docker run -d --name "$DOCKER_CONTAINER_NAME" -p "$APP_PORT":"$APP_PORT" "$DOCKER_IMAGE"

# Check if ngrok is installed
if ! command -v ngrok &> /dev/null; then
  echo "Downloading ngrok..."
  # Adjust the download link for your operating system and architecture
  wget https://bin.equinox.io/c/4VmDzA7iaHb/ngrok-stable-linux-amd64.zip
  unzip ngrok-stable-linux-amd64.zip
  rm ngrok-stable-linux-amd64.zip
  chmod +x ngrok
  echo "ngrok has been downloaded and is ready to use."
fi

# Run ngrok
echo "Running ngrok on port: $APP_PORT..."
NGROK_URL=$(./ngrok http "$APP_PORT" | grep "url.*https" | awk '{print $2}')

if [ -n "$NGROK_URL" ]; then
  echo "Your application is now accessible at: $NGROK_URL"
else
  echo "Failed to obtain ngrok URL. Please check if ngrok is running correctly."
fi

echo "To stop, press Ctrl+C (ngrok) and use 'docker stop $DOCKER_CONTAINER_NAME' and 'docker rm $DOCKER_CONTAINER_NAME'."