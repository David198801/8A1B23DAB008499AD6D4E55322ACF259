pip install xxx ,在特定环境里使用pip，下载的包会存在特定环境的目录里面，例如:

D:\Anaconda3\envs\nlp\Lib\site-packages\fasttex

使用pip uninstall xxx，包就卸载掉了，也可以用conda remove --name nlp --all 来删除环境



conda install xxx ，不管在什么环境下载的包，都统一放在一个目录里面：

D:\Anaconda3\pkgs\fasttext

这个fasttext里面也有site-packages文件夹

在某个环境下面下载了某个包，再到另外一个环境下载同样的包，conda会自动在上面的目录里面找，如果有，就不会重复下载，而是将这个包的site-packages下的文件复制到当前环境下（和直接pip install 一样）



使用conda uninstall xxx时，和pip uninstall一样，删除了当前环境site-packages里面的包内容，但是在上述目录里面还存在这这个包，此时再到另外一个环境下载这个包，还是将site-packages复制一份到当前环境下，做到了一次下载，到处使用。



要真正删除pkgs下的包，可以使用清理未使用的包

```javascript
conda clean -p
```

或

```javascript
conda clean -a
```

或者还原base环境

```javascript
 conda install --revision 0
```



https://www.anaconda.com/blog/understanding-conda-and-pip



Conda包是二进制文件。永远不需要安装可用的编译器。

conda软件包不限于Python软件。它们可能还包含C或C ++库，R软件包或任何其他软件。

| conda | pip | manages |
| - | - | - |
| binaries | wheel or source | can require compilers |
| no | yes | package types |
| any | Python-only | create environment |
| yes, built-in | no, requires virtualenv or venv | dependency checks |


