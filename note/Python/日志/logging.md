

```javascript
import logging

logging.basicConfig(level=logging.INFO,format='%(asctime)s - %(levelname)s: %(message)s')

logging.info("正在请求:" + url)

logging.error("请求发生异常，url:%s",url,exc_info=True)
```

