

```javascript
apt install dislocker
```



```javascript
mkdir /mnt/bit/1600g
mkdir /mnt/hdd/1600g
```



```javascript
dislocker -V /dev/sdb2 -p136598-522456-228415-218801-173756-142395-190762-290774 -- /mnt/bit/1600g
```



```javascript
mount -rw -o loop /mnt/bit/1600g/dislocker-file /mnt/hdd/1600g
```



卸载

```javascript
umount /mnt/hdd_500g
```



```javascript
umount /mnt/hdd_500g_bit # 若busy则检查是否有文件服务还开着
```

