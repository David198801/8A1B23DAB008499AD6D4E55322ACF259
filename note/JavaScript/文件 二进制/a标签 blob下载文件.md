

```javascript
let url = window.URL.createObjectURL(new Blob([balo]))
        let link = document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', file.name)
        document.body.appendChild(link)
```



```javascript
var urlObject = window.URL || window.webkitURL || window.mozURL || window.msURL;
var export_blob = new Blob([data]);
var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a");
save_link.href = urlObject.createObjectURL(export_blob);
save_link.download = name;
save_link.click();
```

