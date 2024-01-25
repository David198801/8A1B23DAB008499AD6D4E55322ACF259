https://askubuntu.com/questions/460885/how-to-clone-only-some-directories-from-a-git-repository/729798#729798

https://stackoverflow.com/questions/600079/how-do-i-clone-a-subdirectory-only-of-a-git-repository



创建空仓库

```javascript
mkdir devops
cd devops
git init  # 初始化
```

拉取远程仓库信息

```javascript
git remote add -f origin http://your/git/repo.git  # 拉取远程仓库信息
```

开启 sparse clone

```javascript
git config core.sparsecheckout true  # 开启 sparse clone
```

设置过滤

```javascript
echo "子目录名称" >> .git/info/sparse-checkout  # 设置过滤条件
```

更新仓库

```javascript
git pull origin master # 拉取仓库
```





或使用filter下载空仓库，再用sparse

```javascript
git clone \
  --depth 1  \
  --filter=blob:none  \
  --sparse \
  https://github.com/cirosantilli/test-git-partial-clone \
;
cd test-git-partial-clone
git sparse-checkout init --cone
git sparse-checkout set d1
```

