#!/bin/bash
##初始化程序目录
mkdir /home/application

##安装java环境
mkdir /home/application/java && cd /home/application/java && wget http://cdn.itnaodong.com/download/jdk-8u201-linux-x64.rpm && rpm -ivh jdk-8u201-linux-x64.rpm 

##安装git环境
yum install -y git 

##安装jenkins
mkdir /home/application/jenkins && cd /home/application/jenkins && wget https://mirrors.tuna.tsinghua.edu.cn/jenkins/war-stable/2.150.3/jenkins.war

##安装nginx
yum install -y nginx

##安装zip支持
yum install zip unzip 

##安装gradle
mkdir /home/application/gradle && cd /home/application/gradle && wget http://services.gradle.org/distributions/gradle-4.10.3-all.zip && unzip gradle-4.10.3-all.zip 

##安装七牛qshell
mkdir /home/application/qiniu && cd /home/application/qiniu && wget http://devtools.qiniu.com/qshell-v2.3.3.zip && unzip qshell-v2.3.3.zip && mv qshell_linux_x64 qshell && chmod 777 qshell

##安装nodejs环境
mkdir /home/application/nodejs && cd /home/application/nodejs && wget https://nodejs.org/dist/v10.15.3/node-v10.15.3-linux-x64.tar.xz && tar xvJf node-v10.15.3-linux-x64.tar.xz && mv node-v10.15.3-linux-x64 v10.15.3 && export NODEJS_HOME=/home/application/nodejs/v10.15.3 && export PATH=$NODEJS_HOME/bin:$PATH && npm config set registry https://registry.npm.taobao.org

##bsdiff环境
mkdir /home/application/bsdiff && cd /home/application/bsdiff && wget http://cdn.itnaodong.com/download/bsdiff-4.3.zip && unzip bsdiff-4.3.zip && cd bsdiff-4.3 && chmod 755 bsdiff bspatch

##文件校验工具
mkdir /home/application/file && cd /home/application/file && wget http://cdn.itnaodong.com/download/fileutil.jar

##JSON转换工具
mkdir /home/application/json && cd /home/application/json && wget http://cdn.itnaodong.com/download/jsonparse.jar

##react native 环境
npm install -g yarn react-native-cli
yarn config set registry https://registry.npm.taobao.org --global
yarn config set disturl https://npm.taobao.org/dist --global
