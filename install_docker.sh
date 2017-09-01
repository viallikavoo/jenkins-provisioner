#!/bin/sh

# Uninstall existing docker if any
apt-get remove -y docker docker-engine

# Install deps
apt-get -y install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common

# Add GPG key
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# Add repository
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

apt-get -y update

# Install docker
apt-get install -y docker-ce
