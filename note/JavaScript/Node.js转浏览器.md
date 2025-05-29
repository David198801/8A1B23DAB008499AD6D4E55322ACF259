

node库转浏览器库

# browserify

http://browserify.org/

在浏览器模式使用node的包，browserify打包node npm包

https://www.cnblogs.com/jackadam/p/17790516.html

```javascript
npm install -g browserify
npm install -D iconv-lite

// main.js
const iconv = require('iconv-lite');  //导入iconv-lite模块
window.iconv = iconv;                 //全局声明常量iconv

npx browserify main.js -o bundle.js

<script src="bundle.js"></script>
```

简化，安装完成后直接执行，-r相当于require

```javascript
npx browserify -r prettier-plugin-sql -o prettier-plugin-sql.js
```







webpack

https://webpack.js.org/



vitejs

https://vitejs.dev/

https://cn.vitejs.dev/guide/