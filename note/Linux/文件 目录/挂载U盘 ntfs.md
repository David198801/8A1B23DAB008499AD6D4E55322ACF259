列出U盘

```javascript
fdisk -l
```

挂载

```javascript
mount /dev/sdb1 /mnt/u1/
```

删除挂载

```javascript
umount /dev/sdb1
```





ntfs

安装ntfs-3g

```javascript
apt install ntfs-3g
```



```javascript
mount -t ntfs-3g /dev/sdb1 /mnt/usb_king/
```

