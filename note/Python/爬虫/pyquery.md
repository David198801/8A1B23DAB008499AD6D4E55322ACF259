

```javascript
from pyquery import PyQuery as pq

r = requests.get(url=url)
doc = pq(r.text)
a_ele = doc('div.name a')

#获取第一个
name = a_ele.text()

#遍历
for i in a_ele:
    name = pq(i).text()
```

