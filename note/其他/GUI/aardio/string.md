string编码区别，默认utf8，常用utf16

utf8和utf16，通过string.toUnicode()/string.fromUnicode转换

ustring基于utf16的string



string底层是API数据类型的数组或指针



utf8 string，BYTE数组（1字节）

```javascript
raw.str( {BYTE bytes[] = {0xE4;0xBD;0xA0;0xE4;0xBD;0xA0}},false)
```

返回"你你你"



utf16 string，WORD数组（2字节无符号）

```javascript
io.print(raw.str( {WORD bytes[] = {0x4f60;0x4f60;0x4f60}},true))
```

返回"你你你"



转义

io.print('Hel\x6c\x6f world\x21')

string.fromUnicode('\x6E\xD8\x1C\xDC')

ustring.pack(0x2B81C)，pack是fromCharCode()的别名

ustring.unpack( '\U02b81c')