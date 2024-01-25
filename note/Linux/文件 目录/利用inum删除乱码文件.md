

```javascript
ls -i
```



```javascript
find -inum 节点号 -delete
```



删除目录,会提示失败，但实际已删除

```javascript
find -inum 节点号 -exec rm -rf {} \;
```

