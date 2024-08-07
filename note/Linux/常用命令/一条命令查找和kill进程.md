kill java
```shell
ps -ef | grep java | grep -v grep | awk '{print $2}' | xargs -r kill -9
```

