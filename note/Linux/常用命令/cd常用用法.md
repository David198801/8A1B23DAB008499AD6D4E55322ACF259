

```javascript
cd常用用法:
 
cd ~     返回当前用户的主目录，与不带~相同
cd /     切换到根目录
cd ..     切换到父级目录
cd ../..     切换到两层父级目录
cd ../DirName 切换到父级目录的某个目录下
cd !$    把上个命令的参数作为cd命令的参数，如: ls /var/tmp
cd -    切换到上次的目录，$OLDPWD 保存着上次进入的目录历史
tab    自动补齐
tab两次    显示目录下的文件和目录

对于有软链接的目录，如：/var/lock - -> /run/lock/
cd 只是切换到 /var/lock 目录，pwd也只是查看到 /var/lock 地址
 
pwd -P 可以查看这个目录链接的地址 /run/lock/
cd -P /var/lock 可以直接切换到这个目录链接的地址 /run/lock/
```

