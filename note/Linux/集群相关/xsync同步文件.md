

```javascript
yum -y install rsync
```



```javascript
vim ~/script/xsync.sh
```



```javascript
#!/bin/bash
#1. 判断参数个数
if [ $# -lt 1 ]
then
  echo Not Enough Arguement!
  exit;
fi
#2. 遍历集群所有机器
for host in centos128 centos129 centos130
do
  echo ====================  $host  ====================
  #3. 遍历所有目录，挨个发送
  for file in $@
  do
    #4 判断文件是否存在
    if [ -e $file ]
    then
      #5. 获取父目录
      pdir=$(cd -P $(dirname $file); pwd)
      #6. 获取当前文件的名称
      fname=$(basename $file)
      ssh $host "mkdir -p $pdir"
      rsync -av $pdir/$fname $host:$pdir
    else
      echo $file does not exists!
    fi
  done
done
```



```javascript
chmod 777 xsync.sh
```



```javascript
vim ~/.bashrc
```



```javascript
alias xsync='/root/script/xsync.sh'
```



```javascript
source ~/.bashrc
```

