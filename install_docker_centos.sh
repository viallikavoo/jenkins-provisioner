sudo curl -fsSL https://get.docker.com/ | sh
sudo groupadd docker
sudo usermod -aG docker osboxes
sudo systemctl enable docker
sudo curl -L https://github.com/docker/compose/releases/download/1.14.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

