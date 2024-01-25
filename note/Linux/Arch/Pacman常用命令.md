Pacman是Arch Linux 的包管理器。它将一个简单的二进制包格式和易用的构建系统结合了起来。不管软件包是来自官方的 Arch 库还是用户自己创建，Pacman 都能方便得管理。

更新系统



在 Archlinux 中，使用一条命令即可对整个系统进行更新：

pacman -Syu

如果你已经使用pacman -Sy将本地的包数据库与远程的仓库进行了同步，也可以只执行：pacman -Su

安装包



➔ pacman -S 包名：例如，执行 pacman -S firefox 将安装 Firefox。你也可以同时安装多个包，

只需以空格分隔包名即可。

➔ pacman -Sy 包名：与上面命令不同的是，该命令将在同步包数据库后再执行安装。

➔ pacman -Sv 包名：在显示一些操作信息后执行安装。

➔ pacman -U：安装本地包，其扩展名为 pkg.tar.gz。

➔ pacman -U http://www.example.com/repo/example.pkg.tar.xz 安装一个远程包（不在 pacman 配置的源里面）

删除包



➔ pacman -R 包名：该命令将只删除包，保留其全部已经安装的依赖关系

➔ pacman -Rs 包名：在删除包的同时，删除其所有没有被其他已安装软件包使用的依赖关系

➔ pacman -Rsc 包名：在删除包的同时，删除所有依赖这个软件包的程序

➔ pacman -Rd 包名：在删除包时不检查依赖。

搜索包



➔ pacman -Ss 关键字：在仓库中搜索含关键字的包。

➔ pacman -Qs 关键字： 搜索已安装的包。

➔ pacman -Qi 包名：查看有关包的详尽信息。

➔ pacman -Ql 包名：列出该包的文件。

其他用法



➔ pacman -Sw 包名：只下载包，不安装。

➔ pacman -Sc：清理未安装的包文件，包文件位于 /var/cache/pacman/pkg/ 目录。

➔ pacman -Scc：清理所有的缓存文件。





