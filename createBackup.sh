#!/bin/bash
docker run -v jenkins_data:/volume -v /tmp:/backup loomchild/volume-backup \
 backup jenkins
 
