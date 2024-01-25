https://github.com/uuidjs/uuid

https://www.bootcdn.cn/uuid/

```javascript
<script src="https://cdn.bootcdn.net/ajax/libs/uuid/8.3.2/uuid.min.js"></script>
```

| uuid.NIL | The nil UUID string (all zeros) | New in uuid@8.3 |
| - | - | - |
| uuid.parse() | Convert UUID string to array of bytes | New in uuid@8.3 |
| uuid.stringify() | Convert array of bytes to UUID string | New in uuid@8.3 |
| uuid.v1() | Create a version 1 (timestamp) UUID | uuid.v3() |
| Create a version 3 (namespace w/ MD5) UUID | uuid.v4() | Create a version 4 (random) UUID |
| uuid.v5() | Create a version 5 (namespace w/ SHA-1) UUID | uuid.validate() |
| Test a string to see if it is a valid UUID | New in uuid@8.3 | uuid.version() |






简单版：

https://www.cnblogs.com/goloving/p/13853524.html

```javascript
function guid2() {
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }
    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}
guid2() // "748eea29-f842-4af9-a552-e1e1aa3ed979"
```



```javascript
function generateUUID() {
    var d = new Date().getTime();
    if (window.performance && typeof window.performance.now === "function") {
        d += performance.now(); //use high-precision timer if available
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;

}```

