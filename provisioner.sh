#!/bin/bash

set -e

#UNIX_USER="ubuntu"
JENKINS_IMAGE_NAME="jenkins-master"
JENKINS_DATA_DIR="jenkins_data"

# Docker setup
systemctl enable docker.service
systemctl start docker.service
#gpasswd -a "$UNIX_USER" docker

#mkdir "$JENKINS_DATA_DIR"
#chown -R ubuntu:ubuntu "$JENKINS_DATA_DIR"

# Build master image
docker volume create $JENKINS_DATA_DIR
docker build -t "$JENKINS_IMAGE_NAME" .

