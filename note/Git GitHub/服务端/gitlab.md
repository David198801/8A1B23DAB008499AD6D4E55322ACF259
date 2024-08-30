# 官网

https://about.gitlab.com/





## 准备工作

### 1、安准基础依赖（这一步基本都可以跳过，很多服务器默认安装）

```nsis
#安装技术依赖
sudo yum install -y curl policycoreutils-python openssh-server

#启动ssh服务&设置为开机启动
sudo systemctl enable sshd
sudo systemctl start sshd
```

### 2、安装Postfix

Postfix是一个邮件服务器，GitLab发送邮件需要用到

```maxima
#安装postfix
sudo yum install -y postfix

#启动postfix并设置为开机启动
sudo systemctl enable postfix
sudo systemctl start postfix
```

### 3、开放ssh以及http服务

根据实际情况执行，公司内部服务器初始时，防火墙就没打开

```jboss-cli
#开放ssh、http服务
sudo firewall-cmd --add-service=ssh --permanent
sudo firewall-cmd --add-service=http --permanent

#重载防火墙规则
sudo firewall-cmd --reload
```





- ## 三、部署过程

  本次我们部署的是社区版:gitlab-ce，如果要部署商业版可以把关键字替换为：gitlab-ee

  ### 1、Yum安装GitLab

- 添加GitLab社区版Package

  ```awk
  curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.rpm.sh | sudo bash 
  ```

- 安装GitLab社区版

  ```cmake
  sudo yum install -y gitlab-ce 
  ```

  安装成功后会看到gitlab-ce打印了*组成的狐狸图案

- #### 异常处理：

  出现“policycoreutils-python is needed by问题”，应该是在安装gitlab时，gitlab的版本是centos7的，如果你确定你的centos版本是centos7
  ​

  **解决方法**：

  ```ebnf
  sudo yum installpolicycoreutils-python
  ```

  如果这个方法无效，你的centos版本一定不是centos7
  如果你的centos版本是centos8.那么换一个gitlab版本，也就是重新下载一个，这里是最新地址

  ```awk
  https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el8/
  ```

  下载命令：

  ```awk
  wget https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el8/gitlab-ce-12.10.1-ce.0.el8.x86_64.rpm
  ```

  安装：

  ```apache
  sudo rpm -i gitlab-ce-12.10.1-ce.0.el8.x86_64.rpm
  ```

  若出现错误“policycoreutils-python-utils is needed by gitlab-ce-12.10.1-ce.0.el8.x86_64”
  解决办法：

  ```cmake
  sudo yum install policycoreutils-python-utils
  ```

  然后重新安装。
  ​

  参考：[https://blog.csdn.net/fu18838928050/article/details/107901895](https://link.segmentfault.com/?enc=SjQRogX4eRUpye0PgV2yDA%3D%3D.ld2VHbIHPPtqwsrHRKjYyC4seqe1oSQ8DEobo%2BWuYjDXNA2uZge%2FBfVlVRoCQRajL1i88eXHl775SyzlLAcujg%3D%3D)

  ### 2、配置GitLab站点Url

  GitLab默认的配置文件路径是/etc/gitlab/gitlab.rb
  默认的站点Url配置项是：

  ```nginx
  external_url 'http://gitlab.example.com'
  ```

  

  这里我将GitLab站点Url修改为"external_url '[http://192.168.0.80:900](https://link.segmentfault.com/?enc=gyY6bZWkbg1TQ2YPuShPyg%3D%3D.6bxC0GSaRDdOtqufdQ2d6OObnbr1I0zY6Fin3ScQlP0%3D)0"
  也可以用IP代替域名，这里根据自己需求来即可

  ```awk
  #修改配置文件
  sudo vi /etc/gitlab/gitlab.rb
  
  #配置首页地址（大约在第15行）
  external_url 'http://192.168.0.80:9000'
  ```

  ### 3、启动并访问GitLab

  - 启动GitLab

    ```apache
    #重新配置并启动
    sudo gitlab-ctl reconfigure
    
    #完成后将会看到如下输出
    Running handlers complete
    Chef Client finished, 432/613 resources updated in 03 minutes 43 seconds
    gitlab Reconfigured!
    ```

  - 访问GitLab

  将设置的域名DNS解析到服务器IP，或者修改本地host将域名指向服务器IP。
  访问：[http://192.168.0.80:9000](https://link.segmentfault.com/?enc=ucfKo8d5XscDEmLHwYrY5Q%3D%3D.YOZDjF1QHYdOAd29WomevqljRFnAAQrs8UHsUgjl%2BhU%3D)



这时候会提示为管理员账号设置密码。管理员账号默认username是root。
设置完成之后即可使用root账号登录，登陆后会进入欢迎界面。





## 四、GitLab常用配置

### 1、邮件配置

配置邮箱可以让GitLab在发生相应事件的时候进行邮件通知
比如配置腾讯企业邮箱如下：

```prolog
gitlab_rails['smtp_enable'] = true
gitlab_rails['smtp_address'] = "smtp.exmail.qq.com"
gitlab_rails['smtp_port'] = 465
gitlab_rails['smtp_user_name'] = "你的邮箱"
gitlab_rails['smtp_password'] = "你的邮箱密码"
gitlab_rails['smtp_domain'] = "exmail.qq.com"
gitlab_rails['smtp_authentication'] = "login"
gitlab_rails['smtp_enable_starttls_auto'] = true
gitlab_rails['smtp_tls'] = true
gitlab_rails['gitlab_email_from'] = '你的邮箱'
```

具体参考：
[https://docs.gitlab.com/omnibus/settings/smtp.html](https://link.segmentfault.com/?enc=QSr8w0LIvkcAjnjWzcnyPg%3D%3D.RG5K6DZKllg2IxDzBu6FAnoeTgQUJ2V96OLwTuXF8utBndaWcE6gZ082A4g4Q8R28orMHEeJSvOGoY7r1%2FIBeQ%3D%3D)

### 2、禁用创建组权限

GitLab默认所有的注册用户都可以创建组。但对于团队来说，通常只会给Leader相关权限。
虽然可以在用户管理界面取消权限，但毕竟不方便。我们可以通过配置GitLab默认禁用创建组权限。

```clean
#修改配置文件
sudo vi /etc/gitlab/gitlab.rb

#开启gitlab_rails['gitlab_default_can_create_group'] 选项，并将值设置为false
### GitLab user privileges
gitlab_rails['gitlab_default_can_create_group'] = false

#保存后，重新配置并启动GitLab
sudo gitlab-ctl reconfigure
```

### 3.配置代码位置

找到git_data_dirs 选型，配置地址

```abnf
git_data_dirs({
   "default" => {
     "path" => "/data/tools/gitlab/data"
    }
})
```

参考链接：[https://docs.gitlab.com/omnibus/settings/configuration.html](https://link.segmentfault.com/?enc=0kkji46cwEVZgOdNMcZLEA%3D%3D.I7%2BzabQ1KrwSLl3qR7JBLTyIxiqk8rQaFWr55ufL0AtLCpLcBRjS1R0NuF8tXzbWPV5TOdPe2XW3%2F3rDJhMgmg%3D%3D)
​

### 4.gitlab 备份

#### 1）修改配置文件

```awk
# 打开gitlab配置文件
vim /etc/gitlab/gitlab.rb

#设置备份文件的保存位置
gitlab_rails['backup_path'] = "/data/tools/gitlab/backup"

#设置备份文件的过期时间，单位为秒，默认7天
gitlab_rails['backup_keep_time'] = 604800
```

执行wq保存后，刷新配置

```ebnf
gitlab-ctl reconfigure
```

#### 2）执行备份命令验证

执行备份命令，去对应的文件路径查看是否已经创建了备份文件

```gauss
gitlab-rake gitlab:backup:create
```

红色字体描述为 gitlab.rb 和gitlab-secrets.json为敏感文件需要手动备份
​

#### 3）通过cron定时备份

方法1、在命令行输入: crontab -e 然后添加相应的任务，wq存盘退出。

```awk
#输入命令crontab -e
crontab -e  
#输入相应的任务
0 2 * * * /opt/gitlab/bin/gitlab-rake gitlab:backup:create CRON=1  
```

方法2、直接编辑/etc/crontab 文件，即vim /etc/crontab，然后添加相应的任务

```awk
# edited by ouyang 2017-8-11 添加定时任务，每天凌晨两点，执行gitlab备份
0  2    * * *   root    /opt/gitlab/bin/gitlab-rake gitlab:backup:create CRON=1
```

重启cron服务

```maxima
systemctl restart crond.service
```



### 5.gitlab-ctl常用命令介绍

| **命令**        | **说明**                                                 |
| --------------- | -------------------------------------------------------- |
| check-config    | 检查在gitlab中是否有任何配置。在指定版本中删除的rb       |
| deploy-page     | 安装部署页面                                             |
| diff-config     | 将用户配置与包可用配置进行比较                           |
| remove-accounts | 删除所有用户和组                                         |
| upgrade         | 升级                                                     |
| service-list    | 查看所有服务                                             |
| once            | 如果GitLab服务停止了就启动服务，如果已启动就不做任何操作 |
| restart         | 重启GitLab服务                                           |
| start           | 如果GitLab服务停止了就启动服务，如果已启动就重启服务     |
| stop            | 停止GitLab服务                                           |
| status          | 查看GitLab服务状态                                       |
| reconfigure     | reconfigure重新配置GitLab并启动                          |

参考链接：
[https://ken.io/note/centos7-gitlab-install-tutorial](https://link.segmentfault.com/?enc=BB0Wtur%2Bp4BcdzcR7zdHsw%3D%3D.H68D1nx%2BNmujbG6Z%2BxsHcwwv%2Bm0NtC43jffP88qsRfaCKaQWSvy0CPbunSezZ7O%2F%2BmHIdCrvqPm2z013lL01eg%3D%3D)