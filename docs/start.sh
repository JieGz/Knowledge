#!/bin/bash
#gradle 环境
export GRADLE_HOME=/home/application/gradle/gradle-4.10.3
export PATH=$GRADLE_HOME/bin:$PATH

#七牛qshell环境
export QSHELL_HOME=/home/application/qiniu
export PATH=$QSHELL_HOME:$PATH

#nodejs 环境
export NODEJS_HOME=/home/application/nodejs/v10.15.3
export PATH=$NODEJS_HOME/bin:$PATH
npm config set registry https://registry.npm.taobao.org --global
npm config set disturl https://npm.taobao.org/dist --global

#bsdiff 环境
export BSDIFF_HOME=/home/application/bsdiff/bsdiff-4.3
export PATH=$BSDIFF_HOME:$PATH

#文件工具环境
export FILE_UTIL_HOME=/home/application/file
export PATH=$FILE_UTIL_HOME:$PATH

#JSON工具环境
export JSON_UTIL_HOME=/home/application/json
export PATH=$JSON_UTIL_HOME:$PATH

#启动Nginx服务
systemctl start nginx

#启动jenkins
cd /home/application/jenkins && nohup java -jar /home/application/jenkins/jenkins.war &
