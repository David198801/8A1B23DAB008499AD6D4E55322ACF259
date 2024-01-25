vimtutor

直接运行命令行，中文版加zh_cn参数，无需安装

```javascript
vimtutor zh_cn
```





中文文档

官网，pdf，tar.gz

https://github.com/yianwillis/vimcdoc

在线

https://yianwillis.github.io/vimcdoc/doc/help.html



安装

解压

```javascript
$ tar -zxvf vimcdoc.tar.gz
```

安装

```javascript
$ ./vimcdoc.sh -i
```



1.vim中文档不会覆盖原英文文档，安装后vim默认使用中文文档。若想使用英文文档，可在vim中执行以下命令：

     set helplang=en

同理，使用以下命令可重新使用中文文档：

     set helplang=cn

2. 帮助文件的文本是utf-8编码的, 如果想用vim直接查看, 需要在~/.vimrc中设置:

   set encoding=utf-8