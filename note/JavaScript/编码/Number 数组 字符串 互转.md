charCodeAt()

String.fromCharCode()

```javascript
"A".charCodeAt(0) // 65
String.fromCharCode(65) // "A"
```

数组

```javascript
"ABC".split("").map(function(c){return c.charCodeAt(0)}) // [65, 66, 67]
[67,68,69].map(function(n){return String.fromCharCode(n)}).join("") // "CDE"
//或
[..."ABC"].map(c => c.charCodeAt(0)) // [65, 66, 67]
String.fromCharCode(...[67,68,69]) // "CDE"
```



BMP以外字符，使用codePointAt()和String.fromCodePoint()

测试字符，麻将字符U+1F004，UTF-16BE：D8 3C DC 04（D83C=55356，DC04=56324）

```javascript
"🀄".codePointAt(0) // 126980
String.fromCodePoint(126980) // "🀄"```

若使用charCodeAt()则会视为两个字符，使用fromCharCode则需要输入对应的4字节utf-16，如String.fromCharCode(55356, 56324)