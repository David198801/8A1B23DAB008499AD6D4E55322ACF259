关键词：JavaScript Blob

前言

在项目的实际过程中，需要下载一个大于10MB的文件, Apigee会直接拒绝此次下载。所以后端需要将大于10MB的文件切割成一个个小于8MB的文件，此时前端需要将切割的文件合并成一个文件后再进行下载。

冲突

在解决的过程中，有两个问题冲突：

1. 对于要下载的这个文件，不清楚这个文件的大小，所以不清楚需要执行几次请求去后台请求数据。若简单的通过for循环去重复请求，则会遇到异步问题。这里可以用闭包解决，但是不提倡。

1. 如何将多个Blob文件合并成同一个。

解决方案：

1. 用递归去解决循环的问题。（具体实现参考下述完整代码）

1. 有两种方式去将Blob拼接

1. 将Blob片段push到一个数组里

```javascript
   const chunckNumber = !fileSize ? 1 : Math.ceil(fileSize / (8 * 1024 *1024));
   let fileData = [];
   const getFileContent = (index) => {
     if (index < chunckNumber) {//chunckNumber指的是需要循环的次数
       getFileContent(id, true, index).subscribe(data => { //getFileContent是一个service请求
         fileData.push(data);  //data是blob属性
         getFileContent(index + 1);
       },
         (err) => {
           this.selectFileId = null;
           this.handleError(err);
         });
     } else {
       this.selectFileId = null;
       const blob = new Blob(fileData, {
         type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
       });
       const link = document.createElement('a');
       link.href = window.URL.createObjectURL(blob);
       link.download = name;
       link.click();
       window.URL.revokeObjectURL(link.href);
     }
   };
   getFileContent(0);
```

- 2.将Blob片段转化为2进制然后合并到一个数组里

```javascript
    const chunckNumber = !fileSize ? 1 : Math.ceil(fileSize / (8 * 1024 *1024))
    let fileData = new Uint8Array([]);
    const getFileContent = (index) => {
      if (index < chunckNumber) { //chunckNumber指的是需要循环的次数
        getFileContent(id, true, index).subscribe(data => { //getFileContent是一个service请求
          data.arrayBuffer().then(buffer => { //返回类型为Blob的data
            fileData = new Uint8Array([
                ...fileData,
                ...new Uint8Array(buffer),
              ]);
            getFileContentx(index + 1);
          })
        });
      } else {
        const blob = new Blob([fileData], {
          type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = name;
        link.click();
        window.URL.revokeObjectURL(link.href);
      }
    };
    getFileContent(0);
```

后言

希望本文会对你有所帮助，如果有什么问题，可在下方留言沟通。





作者：zewweb

链接：https://www.jianshu.com/p/64694675ca95

来源：简书

著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。