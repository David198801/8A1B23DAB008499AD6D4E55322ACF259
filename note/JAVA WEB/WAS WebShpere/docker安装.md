docker run                 #启动容器的命令，后面跟各种参数 + 容器镜像信息

--name websphere           #指定启动的容器名为 websphere

-h test                    #指定主机名，暂时还没发现有什么用，可配可不配

-e UPDATE_HOSTNAME=true    #访问websphere的ip不是localhost时需要此参数

-p 9043:9043               #指定访问端口号

-p 9443:9443 

--restart=always 

-d ibmcom/websphere-traditional:latest  #后台运行该镜像生成的容器









docker run --name was1 -h was1 -e UPDATE_HOSTNAME=true --restart=always --net=host -p 9043:9043 -p 9443:9443 -d ibmcom/websphere-traditional:8.5.5.24 



firewall-cmd --permanent --zone=public --add-port=9043/tcp



https://192.168.56.3:9043/ibm/console/login.do?action=secure



http://127.0.0.1:9043/ibm/console/login.do?action=secure