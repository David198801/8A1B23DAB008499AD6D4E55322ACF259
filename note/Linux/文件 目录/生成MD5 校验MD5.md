```javascript
find ./ -type f -print0 | xargs -0 md5sum > ./my.md5
```



```javascript
md5sum -c my.md5 | grep FAILE
```





生成单独的md5文件，绝对路径

```javascript
find . -type f -print0 | xargs -0 -I@ bash -c "md5sum '@'>'@.md5'"
```

单独检查

```javascript
find . -name "*.md5" -type f -print0 | xargs -0 md5sum -c | grep FAILE
```





生成单独的md5文件，相对路径

md5.sh

```javascript
IFS=$'\n'
for file in $(find . -name "*.md5" -type f)
do
	md5File=${file%%.md5}
	if [ $0 != $file ] && [ ! -e $md5File ]
	then
		rm -f $file
	fi
done

selfDir=$(dirname $0)
cd $selfDir

for file in $(find $(pwd) -type f | grep -v '\.md5')
do
	if [ $0 != $file ] && [ ! -e $file.md5 ]
	then
		cd $(dirname $file)
		fileName=$(basename $file)
		md5sum $fileName > $fileName.md5
		echo $file.md5
	fi
done

```

检查脚本

```javascript
IFS=$'\n'
for file in $(find $(dirname $0) -type f -name '*.md5')
do
	if [ -e ${file%%.md5} ]
	then
		cd $(dirname $file)
		fileName=$(basename $file)
		md5sum -c $fileName | grep FAILE
	fi
done

```

删除MD5

```javascript
IFS=$'\n'
for file in $(find . -name "*.md5" -type f)
do
	rm -f $file
	echo "delete $file"
done
```

