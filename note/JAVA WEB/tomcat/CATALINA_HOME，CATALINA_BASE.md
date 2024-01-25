CATALINA_HOME，CATALINA_BASE环境变量用于一个tomcat程序配置多套环境



CATALINA_HOME为安装目录，如E:\l\web\apache-tomcat-7.0.108

CATALINA_BASE为工作目录



使用CATALINA_BASE

1.新建配置目录

2.复制conf，若配置文件不全(至少要有server.xml和web.xml)也不会去CATALINA_HOME查找，会启动错误

3.建立lib(查找顺序：base>home)、logs、temp、webapps、work，若没有则自动建立

4.运行时指定CATALINA_BASE环境变量

可选：建立bin目录及setenv.sh， setenv.bat和tomcat-juli.jar，(查找顺序：base>home)