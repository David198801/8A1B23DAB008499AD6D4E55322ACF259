html() text()

| html( ) | 无参数 | 用于获取第一个匹配元素的HTML内容或文本内容 |
| - | - | - |
| html(content) | content为元素的HTML内容 | 用于设置所有匹配元素的HTML内容或文本内容 |
| text( ) | 无参数 | 用于获取所有匹配元素的文本内容 |
| text (content) | content为元素的文本内容 | 用于设置所有匹配元素的文本内容 |




html()，相当于innerHTML，不传参获取，传参设置

text()，相当于innerText，不传参获取，传参设置

outerHTML：.prop(‘outerHTML’)