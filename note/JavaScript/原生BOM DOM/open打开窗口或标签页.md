1、window.open(url)或者window.open(url, name),其中name为_blank

- 标准浏览器、IE9+是新标签打开链接url

- ie6-8是新窗口打开链接url

2、window.open(url, name)，其中name为非_blank的其他4个值

　　此时会会在指定窗口或者frame打开链接url

3、window.open(url, name, configration)

　　只要配置了configration，所有浏览器都是新窗口打开链接url