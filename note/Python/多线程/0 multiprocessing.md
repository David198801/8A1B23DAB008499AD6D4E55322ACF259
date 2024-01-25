

```javascript
from multiprocessing.pool import ThreadPool
```



```javascript
pool = ThreadPool(os.cpu_count())
for detail_url in detail_urls:
    pool.apply_async(get_detail, (detail_url,))
pool.close()
pool.join()
print("Sub-process(es) done.")
```





封装subprocess，分组按顺序执行

```javascript
def func(cmd):
    subprocess.call(cmd,shell=cmd[0]=="copy")
    return 1

def callPool(cmdList,threadNum):
    pool = ThreadPool(threadNum)
    for i in cmdList:
        pool.apply_async(func, (i, )) 

    pool.close()
    pool.join()
    print("Sub-process(es) done.")
    
callPool(copyCmdsList,2)
callPool(metaOutCmdsList,2)
callPool(origin2wavCmdsList,2)
callPool(qaacCmdsList,multiprocessing.cpu_count())
callPool(mergeMetaAndM4aCmdsList,2)
```

