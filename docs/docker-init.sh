#!/bin/bash
#如果之前已经安装过docker,先把它移除
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
#1.安装所需的包。yum-utils提供了yum-config-manager 效用，并device-mapper-persistent-data和lvm2由需要 devicemapper存储驱动程序。
yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2
#2.使用以下命令设置稳定存储库。
yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
#3.安装最新版本的Docker CE和containerd
yum install -y  docker-ce docker-ce-cli containerd.io


#4.设置镜像加速
mkdir -p /etc/docker
tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://ul4c6h4j.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
