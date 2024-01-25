https://www.runoob.com/python/python-json.html

json.dumps

```javascript
import json

data = [ { 'a' : 1, 'b' : 2, 'c' : 3, 'd' : 4, 'e' : 5 } ]

data2 = json.dumps(data)
print(data2)
```



json.dump直接输出到文件

```javascript
with open(file_path, "w", encoding="UTF-8") as f:
    json.dump(data, f, ensure_ascii=False, indent=2)
```





读取

json.loads

```javascript
import json

jsonData = '{"a":1,"b":2,"c":3,"d":4,"e":5}';

text = json.loads(jsonData)
print(text)
```

读取文件

```javascript
import json

with open('data.json', 'r', encoding='utf-8') as f:
    data = json.load(f)
    print(data)
```

