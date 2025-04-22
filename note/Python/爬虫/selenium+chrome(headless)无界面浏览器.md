ubuntu中如何安装selenium+chrome(headless)无界面浏览器？

https://www.cnblogs.com/eliwang/p/14357297.html



selenium是一个Web的自动化测试工具，它可以根据我们的指令，让浏览器自动加载页面，获取需要的数据，甚至页面截屏，或者判断网站上某些动作是否发生。但是它自身不带浏览器，不支持浏览器的功能，因此它需要与第三方浏览器结合在一起才能使用。当selenium升级到3.0之后，对不同的浏览器驱动进行了规范。如果想使用selenium驱动不同的浏览器，必须单独下载并设置不同的浏览器驱动。本文以Chrome浏览器为例，需要安装驱动chromedriver

# 一、安装selenium

```
sudo pip3 install selenium
```

# 二、安装Chrome浏览器

- ## 安装依赖

  ```
  sudo apt-get install libxss1 libappindicator1 libindicator7
  ```

- ## 下载安装包

  ```
  wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb #执行命令，下载稳定版Chrome浏览器
  ```

- ## 安装

  ```
  sudo dpkg -i google-chrome*.deb
  sudo apt-get install -f
  ```

# 三、安装chromedriver

- ## 查看Chrome浏览器版本

  ```
  google-chrome --version   #执行该命令获取当前Chrome浏览器版本号
  ```

- ## 下载对应版本chromedriver

  https://googlechromelabs.github.io/chrome-for-testing/

  ```
  wget -N https://storage.googleapis.com/chrome-for-testing-public/135.0.7049.95/linux64/chromedriver-linux64.zip
  ```

- ## 安装unzip，用于解压缩

  ```
  sudo apt-get install unzip
  ```

- ## 解压缩

  ```
  unzip chromedriver_linux64.zip
  ```

- ## 移动chromedriver位置

  ```
  sudo mv chromedriver /usr/local/share/chromedriver
  ```

- ## 建立软链接-----后续创建driver时就不需要再指定executable_path这个参数

  ```
  sudo ln -s /usr/local/share/chromedriver /usr/bin/chromedriver
  ```

# 四、测试（访问百度）

```
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")

driver = webdriver.Chrome(options=chrome_options)
driver.get('https://www.baidu.com/') #访问百度
print(driver.title)
driver.quit() #关闭浏览器
```

1. chrome_options.add_argument("--no-sandbox")
作用：禁用沙箱安全特性。
背景：Chrome 浏览器通常在运行时使用“沙箱”技术来隔离不同的进程，从而提高安全性。在某些环境下，例如 Docker 容器或某些 Linux 系统中，沙箱功能可能会导致浏览器启动失败或出现权限问题。
注意：
虽然禁用沙箱可以解决许多问题，但这样做也会降低浏览器的安全性。因此，在安全性要求高的应用场景中，应谨慎使用。
2. chrome_options.add_argument("--disable-dev-shm-usage")
作用：禁用 /dev/shm 的使用。
背景：在 Linux 系统中，/dev/shm 是一个共享内存文件系统，可以用于提高程序间的通信效率和性能。然而，在某些情况下，特别是在资源有限的容器环境中，/dev/shm 的大小可能不足以满足 Chrome 的需求，这可能导致 Chrome 启动失败或崩溃。
效果：
启用此选项后，Chrome 将不再使用共享内存，而是改用临时文件来存储数据，从而避免因共享内存不足而导致的问题。
总结
这两个选项常用于在资源受限的环境中，以确保 Chrome 浏览器能够顺利启动并正常运行。然而，使用这些选项时要注意安全性和性能的权衡。