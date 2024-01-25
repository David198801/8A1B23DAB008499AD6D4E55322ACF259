https://hub.docker.com/r/bytemark/webdav/

自动创建一个80端口的http服务



示例

```javascript
docker run --restart always -v /srv/dav:/var/lib/dav \
    -e AUTH_TYPE=Digest -e USERNAME=user1 -e PASSWORD=123456 \
    --publish 80:80 -d bytemark/webdav
```



指定宿主路径，映射到默认路径/var/lib/dav/data

指定容器端口

```javascript
docker run --restart always -v /mnt/hdd:/var/lib/dav/data \
    -e AUTH_TYPE=Digest -e USERNAME=lzb -e PASSWORD=lzb \
    --publish 9001:80 -d bytemark/webdav
```





