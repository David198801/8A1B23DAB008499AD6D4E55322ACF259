

```javascript
# 换回默认源（清除所有用户添加的镜像源路径，只保留默认的路径）
conda config --remove-key channels
vim ~/.condarc
```



win先执行

```javascript
conda config --set show_channel_urls yes
```

创建用户目录下的 .condarc：

```javascript
channels:
  - defaults
show_channel_urls: true
default_channels:
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/r
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/msys2
custom_channels:
  conda-forge: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  msys2: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  bioconda: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  menpo: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  pytorch: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
  simpleitk: https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud
```

然后清除索引缓存

```javascript
conda clean -i
```







其他的镜像源

# 中科大镜像源

```javascript
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/pkgs/main/
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/pkgs/free/
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/conda-forge/
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/msys2/
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/bioconda/
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/menpo/
conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/
```



# 阿里镜像源

```javascript
conda config --add channels https://mirrors.aliyun.com/pypi/simple/
```



# 豆瓣的python的源

```javascript
conda config --add channels http://pypi.douban.com/simple/ 
```



# 显示检索路径，每次安装包时会将包源路径显示出来

```javascript
conda config --set show_channel_urls yes

conda config --set always_yes True
```



# 显示所有镜像通道路径命令

```javascript
conda config --show channels
```





从国内镜像源下载包

# 更换后面的源路径和需要安装的包名即可

```javascript
pip install -U pandas -i https://pypi.tuna.tsinghua.edu.cn/simple

pip install -U networkx python-louvain tensorflow-gpu==1.12 -i https://pypi.tuna.tsinghua.edu.cn/simple
```

