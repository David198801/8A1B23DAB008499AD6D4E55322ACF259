http://www.ruanyifeng.com/blog/2019/11/python-asyncio.html

https://www.liaoxuefeng.com/wiki/1016959663602400/1017970488768640



```javascript
import asyncio


async def count():
    print('one')
    await asyncio.sleep(1)
    print("two")


async def main():
    await asyncio.gather(count(), count(), count())


if __name__ == '__main__':
    asyncio.run(main())
```



```javascript
import asyncio
from pyppeteer import launch

async def main():
    browser = await launch()
    page = await browser.newPage()
    await page.goto('http://example.com')
    await page.screenshot({'path': 'example.png'})
    await browser.close()

asyncio.run(main())
```



```javascript
import asyncio
from pyppeteer import launch


async def screenshot(n):
    file_name = 'example' + str(n) + '.png'
    browser = await launch()
    page = await browser.newPage()
    await page.goto('http://example.com')
    await page.screenshot({'path': r'D:\code\py'+"\\"+file_name})
    await browser.close()
    print(file_name+" done!")


async def main():
    await asyncio.gather(screenshot(1), screenshot(2), screenshot(3))


if __name__ == '__main__':
    asyncio.run(main())
```

