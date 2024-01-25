

```javascript
// base64转blob
        const base64ToBlob = function(base64Data) {
            let arr = base64Data.split(','),
                fileType = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]),
                l = bstr.length,
                u8Arr = new Uint8Array(l);
                
            while (l--) {
                u8Arr[l] = bstr.charCodeAt(l);
            }
            return new Blob([u8Arr], {
                type: fileType
            });
        };
        // blob转file
        const blobToFile = function(newBlob, fileName) {
            newBlob.lastModifiedDate = new Date();
            newBlob.name = fileName;
            return newBlob;
        };
        // 调用
        const blob = base64ToBlob(base64Data);
        const file = blobToFile(blob, '123');
```

下载

```javascript
let url = window.URL.createObjectURL(blob ))
document.write('<a href="'+url+'" download>下载</a>')
```

