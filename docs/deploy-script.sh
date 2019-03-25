#!/bin/bash
project_name=$1
active=$2
release_server=$3
server_port=$4
package_name="${project_name}-1.0.war"

run_dir="/home/app/"

#阿里云健康检查
server_disabled_url="slb-check/v1/disabled/true"
server_check_url="slb-check/v1/check"

java_home="/home/application/java/"
jdk_name="jdk-8u201-linux-x64.rpm"

isExecQuerydslTask=true

cd ${project_name}
if [[ $5 = ${isExecQuerydslTask} ]]
then
    echo "==========================gradle querymodels start=========================="
    gradle querymodels
    echo "==========================gradle querymodels end  =========================="
fi


echo "==========================gradle build assemble -x test start=========================="
gradle build assemble -x test
echo "==========================gradle build assemble -x test end  =========================="

cd build/libs

#部署服务器ip，多台用空格隔开
arrayWen=(${release_server})
#for 循环遍历
for var in ${arrayWen[@]};
do
{
    #检查服务器jdk
    ssh root@${var} "java -version;"
} || {
	
	    {
            #检查目录
            ssh root@${var} "mkdir -p "${java_home}
        } || {
       		    echo "服务器"${var}"目录已存在"
   	         }
    
        {
            #下载jdk
     	    scp ${java_home}${jdk_name} root@${var}:${java_home}${jdk_name}
        } || {
                 echo "jdk安装文件已存在"
             }
    
        #安装jdk
        ssh root@${var} "rpm -ivh "${java_home}${jdk_name}
    }

{
    #使准备部署的服务器健康检查失效,以触发SLB将用户流量分配到其他机子,等待20s的时间让流量平滑切换
    echo  "curl --connect-timeout 1 -m 1 -X GET http://${var}:${server_port}/${server_disabled_url}"
    curl --connect-timeout 1 -m 1 -X GET "http://${var}:${server_port}/${server_disabled_url}"
    echo -e "\nsleep 20s"
    sleep 20s
} || {
	    echo "服务器"${var}"正处于关闭状态"
     }

{
    #检查目录
    ssh root@${var} "mkdir -p "${run_dir}
} || {
	    echo "服务器"${var}"目录已存在"
     }

{
    #关闭原有spring boot
    ssh root@${var} "killall -I java"
} || {
	    echo "服务器"${var}"正处于关闭状态"
     }  

#清空包
ssh root@${var} "rm -rf "${run_dir}

#创建目录
{
	#检查目录
	ssh root@${var} "mkdir -p "${run_dir}
} || {
	    echo "服务器"${run_dir}"目录已存在"
     }

echo "开始下载war包"
#下载包
scp ${package_name} root@${var}:${run_dir}${package_name}

echo "启动war包"
#启动包
ssh root@${var} "cd "${run_dir}"; nohup java -jar "${run_dir}${package_name}" --spring.profiles.active="${active}" > log.out 2>&1 &"

#检测检测是否部署成功
flag=1
for i in {1..300};
do 
{
	result=$(curl --connect-timeout 1 -m 1 -X GET http://${var}:${server_port}/${server_check_url})
    if [[ -n "$result" ]]; then
      flag=0
      break
	fi
    
    echo "请求失败，正在选择重试$i"
	sleep 1s
	
} || {
	    echo "请求失败，正在选择重试$i"
	    sleep 1s
     }
done


if [[ ${flag} == 0 ]]
then
   echo "部署成功"
else
   echo "部署失败"
fi

done

echo 执行完成
exit ${flag}
