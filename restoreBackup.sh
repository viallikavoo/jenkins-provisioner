#!/bin/bash
#remove existing volume with same name
docker volume rm -f jenkins_data
#restore backup
docker run -v jenkins_data:/volume -v /tmp:/backup loomchild/volume-backup \
restore jenkins
