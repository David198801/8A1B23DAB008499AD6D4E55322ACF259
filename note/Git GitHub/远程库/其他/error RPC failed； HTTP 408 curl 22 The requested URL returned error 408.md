error: RPC failed； HTTP 408 curl 22 The requested URL returned error: 408

在git push时报错：error: RPC failed; HTTP 408 curl 22 The requested URL returned error: 408
原因：可能是推送的文件太大，要么是缓存不够，要么是网络不行。

将本地 http.postBuffer 数值调整到500MB，可以根据自己的需求设置。
```shell
git config --global http.postBuffer 524288000
```
再次git push成功。

