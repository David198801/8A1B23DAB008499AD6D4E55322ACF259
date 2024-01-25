utf-8明文：

1.encode().decode('unicode-escape')



```javascript
s = r'\xe4\xb8\xad\xe5\x9b\xbd'

c = s.encode().decode('unicode-escape').encode('raw_unicode_escape').decode('utf-8')
print(c)
```



没错，encode().decode('unicode-escape')之后，字符串实际上变成了'\xe4\xb8\xad\xe5\x9b\xbd'，然后就可以用.encode('latin1').decode('utf-8')或.encode('raw_unicode_escape').decode('utf-8')解决

 

2.eval

```javascript
s = r'\xe4\xbd\xa0\xe5\xa5\xbd'
print(eval("'"+s+"'.encode('raw_unicode_escape').decode('utf-8')"))
#写成函数
def getUtf8Escape(s):
　　return eval("'"+s + "'.encode('raw_unicode_escape').decode('utf-8')")
print(getUtf8Escape(s))
```



 

 

Unicode的明文，或者没有\\转义的utf-8（其实就是解码为latin1的字符串）

```javascript
#Unicode明文
a = r'\u8bf7'

b = a.encode().decode("unicode_escape")
print(b)
#或者
b = eval("'"+a+"'")
print(b)


#utf-8
a = '\xe4\xbd\xa0\xe5\xa5\xbd'

b = a.encode('raw_unicode_escape').decode('utf-8')

print(b)
```



b'\\u4f60\\u597d'，等同于'\\u4f60\\u597d'.encode()

```javascript
s=b'\\u4f60\\u597d'
print(s.decode("unicode_escape"))
#或
print(eval("'"+s.decode()+"'"))
```





gbk明文：

```javascript
e = r"\xcf\xb5\xcd\xb3"
print(e.encode().decode('unicode-escape').encode('latin1').decode('gbk'))
#系统
```



```javascript
e = r"\xcf\xb5\xcd\xb3"
print(eval("'"+e+"'").encode('latin1').decode('gbk'))
```

