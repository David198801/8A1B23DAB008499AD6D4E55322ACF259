sftp传输文件 获取其他服务器的文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>sftptest</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
<dependency>
    <groupId>com.jcraft</groupId>
    <artifactId>jsch</artifactId>
    <version>0.1.51</version>
</dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.sftptest.Main</mainClass>
                        </manifest>
                    </archive>
                <descriptorRefs>
                    <descriptorRef>
                        jar-with-dependencies
                    </descriptorRef>
                </descriptorRefs>
            </configuration>
            <!-- 加上下面这一段,使用 mvn package命令,不加则使用mvn assembly-->
                <executions>
                    <execution>
                        <id>make-assemble</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

```java
package com.sftptest;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.Properties;

/**
 * sftp工具类
 * 下载文件
 * 上传文件
 * 删除文件
 */
public class SftpUtil {

    /**
     * 创建一个sftp Session
     *
     * @param host     服务器ip
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return 返回ChannelSftp
     * @throws Exception 获取通道时的异常
     */
    public static ChannelSftp getSftpChannel(String host, Integer port, String username, String password) throws Exception {
        Session session;
        Channel channel = null;
        JSch jSch = new JSch();
        try {
            session = jSch.getSession(username, host, port);
            session.setPassword(password);
            // 配置链接的属性
            Properties properties = new Properties();
            properties.setProperty("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            // 打开会话连接
            session.connect();
            // 获取ftp通道
            channel = session.openChannel("sftp");
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            throw e;
        }
        return (ChannelSftp) channel;
    }

    /**
     * 上传文件
     *
     * @param channelSftp sftp通道
     * @param localFile   本地文件
     * @param remoteFile  远程文件
     */
    public static void upload(ChannelSftp channelSftp, String localFile, String remoteFile) throws Exception {
        try (InputStream inputStream = new FileInputStream(localFile)) {
            channelSftp.put(inputStream, remoteFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("上传文件时找不到本地文件: " + e.getMessage(), e);
        } catch (SftpException e) {
            throw new RuntimeException("上传文件出错: " + e.getMessage(), e);
        }
    }

    /**
     * 从sftp服务器上下载文件
     *
     * @param channelSftp sftp通道
     * @param remoteFile  远程文件
     * @param localFile   本地文件
     */
    public static void download(ChannelSftp channelSftp, String remoteFile, String localFile) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(localFile)) {
            SftpProgressMonitor monitor = new SftpProgressMonitor() {
                private long max = 0;
                private long count = 0;
                private long percent = 0;

                @Override
                public void init(int op, String src, String dest, long max) {
                    this.max = max;
                    System.out.println("开始下载文件: " + src + " 到 " + remoteFile + "，总大小: " + max + " bytes");
                }

                @Override
                public boolean count(long count) {
                    this.count += count;
                    long percentNow = this.count * 100 / max;
                    if (percentNow > percent) {
                        percent = percentNow;
                        System.out.println("下载进度: " + percent + "%");
                    }
                    return true;
                }

                @Override
                public void end() {
                    System.out.println("文件下载完成！");
                }
            };

            channelSftp.get(remoteFile, outputStream, monitor);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("下载文件时找不到本地文件: " + e.getMessage(), e);
        } catch (SftpException e) {
            throw new RuntimeException("下载文件出错: " + e.getMessage(), e);
        }
    }

    /**
     * 删除文件
     *
     * @param channelSftp sftp通道
     * @param remoteFile  远程文件
     */
    public static void deleteFile(ChannelSftp channelSftp, String remoteFile) throws Exception {
        try {
            channelSftp.rm(remoteFile);
        } catch (SftpException e) {
            throw new RuntimeException("删除文件出错: " + e.getMessage(), e);
        }
    }

    /**
     * 关闭sftp链接
     *
     * @param channelSftp sftp通道
     * @throws Exception 关闭session的异常
     */
    public static void closeSession(ChannelSftp channelSftp) throws Exception {
        if (channelSftp == null) {
            return;
        }
        Session session = null;
        try {
            session = channelSftp.getSession();
        } catch (JSchException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }

    public static void main(String[] args) {
        try {
            // 获取sftp通道
            ChannelSftp channelSftp = SftpUtil.getSftpChannel("192.168.0.99", 22, "root", "123456");
            try {
                // 上传文件
                String local = "D:\\11.png";
                String remote = "/11.png";
                SftpUtil.upload(channelSftp, local, remote);
            } catch (SftpException e) {
                // 处理上传文件异常
                System.out.println("上传文件出错：" + e.getMessage());
            }

            try {
                // 下载文件
                String remote2 = "/11.png";
                String local2 = "D:\\tech\\11.png";
                SftpUtil.download(channelSftp, remote2, local2);
            } catch (SftpException e) {
                // 处理下载文件异常
                System.out.println("下载文件出错：" + e.getMessage());
            }

            try {
                // 删除文件
                String remote3 = "/11.png";
                SftpUtil.deleteFile(channelSftp, remote3);
            } catch (SftpException e) {
                // 处理删除文件异常
                System.out.println("删除文件出错：" + e.getMessage());
            }

            try {
                // 关闭连接
                SftpUtil.closeSession(channelSftp);
            } catch (Exception e) {
                // 处理关闭连接异常
                System.out.println("关闭连接出错：" + e.getMessage());
            }
        } catch (JSchException e) {
            // 处理获取SFTP通道异常
            System.out.println("获取SFTP通道出错：" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```

```java
public class Main {

    public static void main(String[] args) throws Exception {
        ChannelSftp channel = SftpUtil.getSftpChannel("10.1.95.153", 22, "acs", "putty#123");
        SftpUtil.download(channel,"/home/acs/ACS.war","d:/test1/ACS.war");
        SftpUtil.closeSession(channel);
    }
}
```

