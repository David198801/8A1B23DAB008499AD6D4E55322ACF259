如何拯救一台glibc被干掉的Linux服务器?

https://zhuanlan.zhihu.com/p/20062978





今天正要下班，旁边部门的PM过来问我：

『我们有个小伙子把生产系统上的glibc给删了，现在什么命令都跑不了了，还有救吗？』

(更正：今天又问了一下，是正在准备要上线的服务器，所以还不是生产系统)

我说，glibc也能弄掉，莫不是rpm --force?

回答是肯定的。

glibc被卸载，负责加载所有.so的ld.so也就没了，因此运行几乎所有外部命令时都会得到一句『找不到ld-linux-x-y-z.so.2』的出错提示。比如ls，比如cp，以及所有动态链接的命令。

这是一台放置于另外一个大洲的客户IDC的物理服务器。我说不行就光盘引导修复，但不知道什么原因他们又连不上服务器的HP iLO工具。

干着急也不是办法。万幸的是rpm --force的小伙子的ssh登录shell还连着。我说那不行就只能你自己一个byte一个byte先敲一个static linked的binary出来，这是可以运行的。

话说完，我就大概想到该怎么办了。

1. 用bash的内部命令 printf '\xaa\xbb\xcc' > file 可以生成任意内容的文件
2. 另外找台同配置的Linux，用xxd或hexdump配合一点点脚本，或者直接用python写个小脚本，把ld.so文件转储成若干条printf '...' >> file的命令（考虑到bash单行命令的长度限制，我没有尝试只生成一条命令）
3. copy 2)中生成的命令，paste到出事的Linux shell中运行
4. 这样至少ld.so能用，接下来可以按图索骥恢复其他.so

Tada! 我感觉自己重新发明了scp。

然而这样行不通。printf重定向生成的文件不带可执行位，无法被执行，只是把出错信息变成了permission denied而已。

别忘了chmod也不能用哦。

所以往上面手动恢复glibc这条路看来是行不通了。

既然动态链接的命令都不能用，那就只能上静态链接了。到[http://www.busybox.net](http://www.busybox.net/)下载了静态链接的1.16.0版（越旧的版本越好——因为越小）的busybox，不到900KB，用上面的办法，转存到出事的Linux上。

刚才不是说了没有可执行位吗？busybox又怎样？

这次，我是把busybox直接写入到

```text
printf '...' > /bin/cp
```

覆盖系统原有的带x位的cp文件，用旧瓶装新酒，我终于获得了一个可执行的busybox!

别忘了，argv[0]为cp时，busybox就是在做cp的事情！

因此接下来再

```text
cp cp ln
ln -s cp chmod
ln -s cp ls
ln -s cp wget
ln -s cp sh
...
```

printf和busybox拯救世界！

再传送一份静态连接的dropbear上去，起一个备用的ssh server（别忘了把账号的登录shell改成busybox版sh），总算可以松一口气，继续后面的灾难恢复了。