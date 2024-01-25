cp命令加了“-f”仍然会有提示

cp命令中的[-f]选项指：覆盖目标同名文件或目录时不进行提醒，直接强制覆盖。



但是有时候加了-f，仍然会问你是否覆盖，依次确认非常麻烦。

原因是，此时的cp实际上是 cp -i 的别名。

可以在系统中用alias指令，查询当前cp指令默认值

如下：

1.查询当前cp指令默认值

```javascript
alias cp
```

结果为cp -i

2修改cp的别名

```javascript
alias cp="cp"
alias cp
```

这样就成功解决了

