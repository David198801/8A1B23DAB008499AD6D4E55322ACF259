清空提交记录 git rebase --hard

https://www.cnblogs.com/xfstu/p/17646192.html



如何删除分支上的文件（包含历史文件）

https://www.cnblogs.com/minskiter/p/14691824.html

```javascript
git filter-branch --force --index-filter "git rm --cached --ignore-unmatch 'note/密码/我的密码.md' " --prune-empty --tag-name-filter cat -- --all
```

从仓库中删除敏感数据

https://docs.github.com/cn/enterprise-server@2.21/github/authenticating-to-github/keeping-your-account-and-data-secure/removing-sensitive-data-from-a-repository





https://blog.csdn.net/u013553529/article/details/88087047

1.reset，回退到某个commit

回退单个文件

```javascript
git reset  fcd2093 a.jsp
```

再提交即可



2.revert，创建一个新的commit，将某个commit的操作取消

```javascript
git revert 0ad5a7a6
```





3.git rebase -i，真正删除某个commit，用于误将密码等重要信息commit、合并多个commit为一个完整commit 等情况

https://www.jianshu.com/p/4a8f4af4e803

```javascript
 git rebase -i  [startpoint]  [endpoint]
```

其中-i的意思是--interactive，即弹出交互式的界面让用户编辑完成合并操作，[startpoint] [endpoint]则指定了一个编辑区间，如果不指定[endpoint]，则该区间的终点默认是当前分支HEAD所指向的commit(注：该区间指定的是一个前开后闭的区间)。

在查看到了log日志后，我们运行以下命令：

```javascript
$ git rebase -i HEAD~3
pick f7f3f6d changed my name a bit
pick 310154e updated README formatting and added blame
pick a5f4a0d added cat-file
```

pick：保留该commit（缩写:p）

reword：保留该commit，但我需要修改该commit的注释（缩写:r）

edit：保留该commit, 但我要停下来修改该提交(不仅仅修改注释)（缩写:e）

squash：将该commit和前一个commit合并（缩写:s）

fixup：将该commit和前一个commit合并，但我不要保留该提交的注释信息（缩写:f）

exec：执行shell命令（缩写:x）

drop：我要丢弃该commit（缩写:d）



删除commit a5f4a0d，就是pick改为drop或把『pick a5f4a0d added cat-file』这一行删掉。如下，

```javascript
pick f7f3f6d changed my name a bit
pick 310154e updated README formatting and added blame
```

保存并退出编辑器，git就把 commit a5f4a0d删掉了。



需要说明的是，如果只是删掉『a5f4a0d added cat-file』，不需要使用HEAD~3，使用HEAD~1就行，因为commit a5f4a0d是最后一个提交，是HEAD指向的提交。