利用inum(inode)删除乱码文件



1. 用ls或find输出节点号

```javascript
ls -i
```



```javascript
find -inum 节点号 -delete
```



2. 用find删除文件/目录,会提示失败，但实际已删除

```javascript
find -inum 节点号 -exec rm -rf {} \;
```

文件删不掉就删上层目录
