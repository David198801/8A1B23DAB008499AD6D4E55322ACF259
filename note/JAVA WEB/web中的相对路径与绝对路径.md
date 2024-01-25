相对路径：

. 表示当前路径

.. 表示上级路径



/ 在浏览器中表示服务器地址，在servlet中表示工程地址（若是重定向则是由浏览器解析 / 获得服务器地址），在本地（如浏览器file://、资源管理器、cmd、JAVA File类）中表示盘符地址，在classloader中表示classpath



相对路径使用./或不写，使用/会出错

eg. 若当前目录下的a文件夹中有a.html，则使用 ./a/a.html 或 a/a.html，使用 /a/a.html是错误的

