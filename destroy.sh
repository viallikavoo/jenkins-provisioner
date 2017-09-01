#!/bin/bash
docker rm -f jenkins-master
docker rmi -f jenkins-master
docker system prune -f
docker volume rm -f jenkins_data

