FROM jenkinsci/jenkins:2.71

COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN xargs /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Install jenkins job builder
USER root
RUN apt-get update && \
	apt-get install -y python-pip python-dev build-essential && \
	pip install --upgrade pip && \
	pip install jenkins-job-builder


# Java parameters
ENV JAVA_OPTS="-Xmx256m -Dfile.encoding=UTF8 -Dorg.apache.commons.jelly.tags.fmt.timeZone=Europe\/Copenhagen -Djenkins.install.runSetupWizard=false"

EXPOSE 8080
EXPOSE 50000

USER jenkins

# Have the volume command as the last one. Docker will
# Take care of moving everything into the volume

# In this case we are only installing all jenkins plugins.
# When docker run is used docker will make sure that the
# plugins are moved to the volume
VOLUME ["/var/jenkins_home"]
