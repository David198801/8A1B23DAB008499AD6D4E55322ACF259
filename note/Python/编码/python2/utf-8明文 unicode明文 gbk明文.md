utf-8明文：

1.decode("string_escape")

```javascript
c = "\\xe4\\xb8\\xad"
print c.decode("string_escape") # 中
```



2.eval()

```javascript
c = "\\xe4\\xb8\\xad"
s = eval("'"+c+"'")
print s
```



 

 

Unicode的明文，

```javascript
#Unicode明文
a = '\u7bb1'#或a = r'\u7bb1'
print a.decode("unicode_escape") #箱

#或者
b = eval("u'"+a+"'")
print b #箱

```

没有\\转义的utf-8在Python2中不需要转换

```javascript
#utf-8
a = '\xe4\xbd\xa0\xe5\xa5\xbd'
print a #箱
```





gbk明文：

```javascript
e = r"\xcf\xb5\xcd\xb3"
print eval("'"+e+"'").decode("gbk") # 系统
```

