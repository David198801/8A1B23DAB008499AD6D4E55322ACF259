https://blog.csdn.net/mengxianglong123/article/details/112694828



我们在使用 git clone + 远程仓库地址将项目下载下来之后，倘若远程仓库有多个分支，我们会发现，使用git branch查看本地分支时，只有一个master分支



```javascript
$ git branch
```

会出现:

* master



但是，大多时候我们是需要在其他分支进行工作的，所以我们需要将远程的其他分支拉下来才可以，首先，先使用git branch -a查看远程分支,那些以rmotes开头且为红色的都是远程分支，或者理解为这些分支是隐藏的



```javascript
$ git branch -a
```

* master

  remotes/origin/HEAD -> origin/master

  remotes/origin/develop

  remotes/origin/feature

  remotes/origin/feature-im

  remotes/origin/master

  remotes/origin/newbranch



如果想要将远程分支与本地分支联系起来，则执行（以feature分支为例）

它会创建新分支并关联远程分支

```javascript
$ git checkout -b feature origin/feature
```

现在

```javascript
$ git branch
```

会出现

* feature

  master



或者使用-t参数，它默认会在本地建立一个和远程分支名字一样的分支



```javascript
$ git checkout -t origin/feature
```

直接checkout也行

```javascript
git checkout feature
```

参考：https://www.jianshu.com/p/6960811ac89c



