使用网页

https://hub.docker.com/

或简单爬虫，获取debian，改为要查询的镜像

列出前几个

```javascript
curl 'https://registry.hub.docker.com/v2/repositories/library/debian/tags/'|jq '."results"[]["name"]'
```

列出所有

```javascript
i=0

while [ $? == 0 ]
do 
   i=$((i+1))
   curl https://registry.hub.docker.com/v2/repositories/library/debian/tags/?page=$i 2>/dev/null|jq '."results"[]["name"]'

done
```

