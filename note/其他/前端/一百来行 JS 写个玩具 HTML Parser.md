https://zhuanlan.zhihu.com/p/338772106



本文教你如何写一个玩具的 html parser（不考虑很多边界情况），核心思路模仿于

Let's build a browser engine! 的一个章节。

0. 先看看效果

不想看可以直接跳过。

```javascript
<div key="value" lqj="nb" >
  gogogo
  <div cool >
    12 3
  </div>
  12 3
</div>
<span>123</span>
```

解析之后

```javascript
[
  {
    "tag": "div",
    "attrs": {
      "key": "value",
      "lqj": "nb"
    },
    "children": [
      " gogogo ",
      {
        "tag": "div",
        "attrs": {
          "cool": ""
        },
        "children": [
          " 12 3 "
        ]
      },
      " 12 3 "
    ]
  },
  " ",
  {
    "tag": "span",
    "attrs": {},
    "children": [
      "123"
    ]
  }
]
```

1. 准备工作

1.1 首先我们造一个 Parser 的 class

```javascript
class HTMLParser {
  constructor () {
    this.input = '' // 要扫描的字符串
    this.cur = 0 // 当前要扫描的位置
  }
  
  get eof () { // 内容是否都扫描完了
    return this.cur >= this.input.length
  }
}
```

1.2 我们在解析的过程中有的时候要往前看几个字符，所以增加一个 peek 方法

```javascript
peek (offset = 0) {
  return this.input[this.cur + offset]
}

// 对于 'abc', peek() === 'a'
// peek(1) === 'b'
```

1.3 我们在解析的时候需要逐渐消耗一些字符，把字符转化成树状数据结构

安排 consumeChar、consumeWhile、consumeSpace 几个方法。

```javascript
consumeChar (c) { // 消耗一个特定字符
  const curChar = this.peek()
  if (curChar === c) {
    this.cur++
  } else {
    throw new Error(`Unexpected character: '${curChar}' should be '${c}'`)
  }
  return curChar
}

const spaceRegex = /\s/ // 匹配空格字符的正则表达式，写在 class 外面

consumeSpace () { // 从当前位置开始，消耗遇到的全部空格，直到遇到非空格
  this.consumeWhile(spaceRegex)
}

// 从当前位置开始，消耗一个特定的符合正则测试的字符串
// 例如对于 'abc 123'
// consumeWhile(/[a-z]/) 会返回 'abc'
consumeWhile (regex) {
  const result = []
  do {
    const curChar = this.peek()
    if (regex.test(curChar)) {
      result.push(curChar)
      this.cur++
    } else {
      break
    }
  } while (!this.eof)
  return result.join('')
}
```

2. 解析工作

先假设我们随手写了个 HTML 片段，我们要让 Parser 能解析这段代码

```javascript
<div key="value" lqj="nb" >
  gogogo
  <div cool >
    12 3
  </div>
  12 3
</div>
<span>123</span>
```

2.1 首先需要一个解析入口函数 parse

```javascript
parse (input) {
  // 初始化
  this.input = input
  this.cur = 0
  // 根可能有一堆节点，所以我们需要实现一个 parseNodes 方法
  return this.parseNodes()
}
```

2.2 解析很多个节点，需要一个 parseNodes 方法

```javascript
parseNodes () {
  const nodes = []
  do {
    let node
    if (this.peek() === '<') {
      // 碰见 < 说明要开起一个 tag 了
      if (this.peek(1) === '/') break
      // 碰见 </ 说明我们在同层的 nodes 都解析完了，可以走了
      node = this.parseElement()
    } else {
      // 剩下的状况当成文本节点解析
      node = this.parseTextNode()
    }
    nodes.push(node)
  } while (!this.eof)
  return nodes
}
```

2.3 解析文本节点，需要一个 parseTextNode 方法

```javascript
parseTextNode () {
  // 很简单，把不是 < 的字符收集起来
  const text = this.consumeWhile(/[^<]/)
  return text.replace(/[\s\n]+/g, ' ')
}
```

2.4 解析 Element 节点，需要一个 parseElement 方法

```javascript
parseElement () {
  // 吃掉一个开标签
  this.consumeChar('<')
  // 拿到 tag
  const tag = this.parseTag()
  this.consumeSpace()
  // 拿到属性
  const attrs = this.parseAttrs()
  this.consumeChar('>')
  // 吃掉子节点
  const children = this.parseNodes()
  // 吃掉一个闭标签
  this.consumeChar('<')
  this.consumeChar('/')
  const closeTag = this.parseTag()
  this.consumeSpace()
  this.consumeChar('>')
  return {
    tag,
    attrs,
    children
  }
}
```

2.5 解析标签，需要一个 parseTag 方法

```javascript
const tokenRegex = /[a-zA-Z0-9\-]/ // 英文字母、数字、中划线

parseTag () {
  // 很简单，拿到一个 tag
  const tag = this.consumeWhile(tokenRegex)
  return tag
}
```

2.6 解析属性，需要 parseAttrs 方法

```javascript
parseAttrs () {
  const attrs = {}
  // 在没遇到 > 的时候说明还在 <  > 里面，可以一个一个的把属性收集起来
  while (this.peek() !== '>') {
    // 重复利用一下 parseTag，获取标签名
    const name = this.parseTag()
    if (this.peek() === '=') {
    // 碰到等于号的情况：< name="value" >
      this.consumeChar('=')
      this.consumeChar('"')
      const value = this.consumeWhile(/[^"]/)
      this.consumeChar('"')
      attrs[name] = value
    } else {
    // 没有等于号：< name >
      attrs[name] = ''
    }
    this.consumeSpace()
  }
  // 返回收集到的属性
  return attrs
}
```

3. 凑活能跑

写一个 print 方法测试一下 Parser：

```javascript
print (input) {
  console.log(JSON.stringify(this.parse(input), 0, 2))
}
```

把我们开头的例子作为输入 print 一下：

```javascript
[
  {
    "tag": "div",
    "attrs": {
      "key": "value",
      "lqj": "nb"
    },
    "children": [
      " gogogo ",
      {
        "tag": "div",
        "attrs": {
          "cool": ""
        },
        "children": [
          " 12 3 "
        ]
      },
      " 12 3 "
    ]
  },
  " ",
  {
    "tag": "span",
    "attrs": {},
    "children": [
      "123"
    ]
  }
]
```

这个时候这个 parser 就凑活能跑了。

本文全部代码在这里

upbeat-cerf-7xmoo - CodeSandbox

​codesandbox.io/s/upbeat-cerf-7xmoo?file=/src/index.js