

```javascript
chmod 755 xxx.rpm.bin
./xxx.rpm.bin
rpm -ivh xxx.rpm
```

https://www.linuxidc.com/Linux/2014-10/107725.htm



Linux安装JDK步骤1. 先从网上下载jdk(jdk-1_5_0_02-linux-i586.rpm) ，推荐SUN的官方网站www.sun.com，下载后放在/home目录中，当然其它地方也行。



进入安装目录



#cd /home



#cp jdk-1_5_0_02-linux-i586.rpm /usr/local



#cd /usr/local



给所有用户添加可执行的权限



#chmod +x jdk-1_5_0_02-linux-i586.rpm.bin



#./jdk-1_5_0_02-linux-i586.rpm.bin



此时会生成文件jdk-1_5_0_02-linux-i586.rpm，同样给所有用户添加可执行的权限



#chmod +x jdk-1_5_0_02-linux-i586.rpm



安装程序



#rpm -ivh jdk-1_5_0_02-linux-i586.rpm



出现安装协议等，按接受即可。



Linux安装JDK步骤2.设置环境变量。



#vi /etc/profile



在最后面加入



#set java environment



JAVA_HOME=/usr/java/jdk-1_5_0_02



CLASSPATH=.:＄JAVA_HOME/lib.tools.jar



PATH=＄JAVA_HOME/bin:＄PATH



export JAVA_HOME CLASSPATH PATH



保存退出。



要使JDK在所有的用户中使用，可以这样：



vi /etc/profile.d/java.sh



在新的java.sh中输入以下内容：



#set java environment



JAVA_HOME=/usr/java/jdk-1_5_0_02



CLASSPATH=.:＄JAVA_HOME/lib/tools.jar



PATH=＄JAVA_HOME/bin:＄PATH



export JAVA_HOME CLASSPATH PATH



保存退出，然后给java.sh分配权限：chmod 755 /etc/profile.d/java.sh



Linux安装JDK步骤3.在终端使用echo命令检查环境变量设置情况。



#echo ＄JAVA_HOME



#echo ＄CLASSPATH



#echo ＄PATH



4.检查JDK是否安装成功。



#java -version