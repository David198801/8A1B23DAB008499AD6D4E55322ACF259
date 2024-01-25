1.虚拟机



https://docs.docker.com/desktop/windows/install/

2.wsl1+Docker Desktop for Windows(Hyper-V backend and Windows containers)

https://blog.csdn.net/xubuwei/article/details/102961686

https://www.cnblogs.com/sfnz/p/15429096.html

3. win10 2004+：

https://www.cnblogs.com/360linux/p/13662355.html

3.1 wsl2+Docker Desktop for Windows(Docker Desktop WSL 2 backend)，官方推荐

| hyper-v  &gt;&gt; | wsl2(linux内核)  &gt;&gt; | ubuntu/centos等  &gt;&gt; | /var/run/docker.sock |
| - | - | - | - |
| 挂载 | hyper-v  &gt;&gt; | windows  &gt;&gt; | dockerd |


   

         

3.2 wsl2+docker

hyper-v >> wsl2(linux内核) >> ubuntu/centos等 >> docker



