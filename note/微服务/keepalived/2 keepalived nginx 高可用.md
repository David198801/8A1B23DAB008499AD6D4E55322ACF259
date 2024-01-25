https://blog.51cto.com/maoqiu/1405675

https://zhuanlan.zhihu.com/p/148136167



多个nginx，主服务器nginx挂掉后自动切换到备份服务器



实例

https://segmentfault.com/a/1190000002881132

https://www.jianshu.com/p/a8b3ce8d1dbc



需要

1.两个虚拟机，配置nginx

2.keepalived

3.虚拟ip



0.1 安装keepalived

0.2 clone一份虚拟机，配置好固定ip

1.配置keepalived

主机keepalived配置，从机修改vrrp_instance VI_1的state为BACKUP，priority为90，

```javascript
! Configuration File for keepalived

# 全局定义
global_defs {
   notification_email {
     acassen@firewall.loc
     failover@firewall.loc
     sysadmin@firewall.loc
   }
   notification_email_from Alexandre.Cassen@firewall.loc
   smtp_server 192.168.200.1
   smtp_connect_timeout 30
   router_id LVS_DEVEL #路由id，可以写ip名
   vrrp_skip_check_adv_addr
   vrrp_strict
   vrrp_garp_interval 0
   vrrp_gna_interval 0
}

# 检测脚本
vrrp_script check_nginx {

  script "/etc/keepalived/check_nginx.sh"

  interval 3 #执行间隔

  weight 2

}

# 虚拟ip
vrrp_instance VI_1 {
    state MASTER #状态，MASTER/BACKUP
    interface ens33 #网卡
    virtual_router_id 51 # 主/备机的virtual_router_id要相同，相同的VRID为一个组，他将决定多播的MAC地址
    priority 100 #主备机取不同优先级，主机值较大，备份机值较小
    advert_int 1 #心跳包间隔
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress { #虚拟ip地址
        192.168.116.50
    }
    track_script {				
        check_nginx				# 你上面定义的名字叫check_nginx 
    }
}

```

check_nginx.sh

```javascript
#!/bin/bash
counter=$(ps -C nginx --no-heading|wc -l)
if [ "${counter}" = "0" ]; then
    /usr/local/nginx/sbin/nginx
    sleep 2
    counter=$(ps -C nginx --no-heading|wc -l)
    if [ "${counter}" = "0" ]; then
        systemctl stop keepalived
    fi
fi
```



2.两台机器上，启动nginx，启动keepalived

```javascript
/usr/local/nginx/sbin/nginx
systemctl start keepalived
```

3.现在任意一台机器nginx关闭，keepalived会根据脚本尝试启动nginx，启动失败则关闭自己

当一台机器的keepalived关闭后，会自动使用另一台机器响应

注意如果脚本配置错误，nginx关闭而keepalived未关闭，会导致无法切换到备用机器