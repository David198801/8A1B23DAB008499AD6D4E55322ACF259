1. python 安装 pyautogui 库，不懂的百度一下怎么安装，这里就不赘述了



扫雷软件使用Minesweeper Arbite，很多扫雷大神都是用这个扫雷，几百K的软件自带计时和回放功能，非常好用。



2.  截图软件的数字1-8，旗子，雷，未点开的空格，点开的空格，寻找规律，发现每个格子像素都是16*16，并且颜色各不相同，pyautogui如果使用图片做对比，运行速度会非常慢，所以找像素点的判断条件，可以发现这些格子很容易通过像素区分出来。除了数字7和旗子，未点开的空格和点开的空格还需判断别的像素，其余只需判断一个像素点。































二、脚本设计



脚本流程为：



1.初始化局面，找到扫雷窗口

2.主循环开始，读取扫雷窗口数字信息

3.用中心方法找雷和找空格

4.用重叠方法找雷和找空格



5.如果两种方法都没有找到雷，说明现有条件不能判断雷和空格在哪里，从剩下的空白格子随机点一个（看运气）



6.重复 2.3.4.5 直到找到99个雷



三、代码块解析



1.初始化函数



```javascript
import pyautogui
import random
from time import sleep

miner_x, miner_y = 30, 16
width, height = 16, 16


def game_init():
    # 初始脚本：初始化列表信息，寻找扫雷窗口，开始运行脚本
    global mines, mines_sign, mines_judge
    mines = []  # 存储未点开格子BOX信息的列表
    mines_sign = [-1 for i in range(miner_x * miner_y)]  # 存储格子是否被点了的信息，0-8为雷的个数，-1为未点开，9为旗子, 10为雷
    mines_judge = [i for i in range(miner_x * miner_y)]  # 若某个格子已经得出结论，则删除该数的序号，以后无须再判断

    while True:
        minesweeper = pyautogui.locateOnScreen('minesweeper.png', confidence=0.99)
        if minesweeper:
            break
    for i in pyautogui.locateAllOnScreen('-1.png', grayscale=True):
        mines.append(i)
    global x_0, y_0
    x_0, y_0 = minesweeper.left, minesweeper.top  # 扫雷区域起始坐标
    click_luck(-1)  # 随机点机格子，直到点开一片
```

定义初始化函数，功能为初始化几个需要用到的列表，找到扫雷窗口，随机点格子直到满足判断雷和空格的条件



2.主循环函数



```javascript
def run_game():
    game_init()
    # 脚本循环开始
    while True:
        pyautogui.moveTo(100, 100)
        get_nums()  # 读取扫雷窗口当前所有信息，大约耗时0.02秒
        a = get_mine_last()
        solve_a()
        solve_b()
        b = get_mine_last()
        if a == b:
            clikc_random()  # 若a方法和b方法均不能判断，即出现需要蒙的情况，则随机点一个（无奈之举）
        if get_mine_last() == 99:
            for i in mines_judge:
                if mines_judge[i] == -1:
                    click(i)
            print('通关扫雷高级')
            return
```

主循环函数主要是流程控制



3.读取屏幕信息函数



```javascript
def get_nums():
    screenshot = pyautogui.screenshot(region=(x_0, y_0, 480, 256))
    for index in mines_judge:
        x, y = index // miner_x, index % miner_x
        pix = screenshot.getpixel((y * 16 + 10, x * 16 + 12))
        if pix == (192, 192, 192):
            pix_0 = screenshot.getpixel((y * 16, x * 16))
            if pix_0 == (255, 255, 255):
                mines_sign[index] = -1
                continue
            elif pix_0 == (128, 128, 128):
                mines_sign[index] = 0
                continue
        elif pix == (0, 0, 255):
            mines_sign[index] = 1
            continue
        elif pix == (0, 128, 0):
            mines_sign[index] = 2
            continue
        elif pix == (255, 0, 0):
            mines_sign[index] = 3
            continue
        elif pix == (0, 0, 128):
            mines_sign[index] = 4
            continue
        elif pix == (128, 0, 0):
            mines_sign[index] = 5
            continue
        elif pix == (0, 128, 0):
            mines_sign[index] = 6
            continue
        elif pix == (0, 0, 0):
            pix_7 = screenshot.getpixel((y * 16 + 7, x * 16 + 7))
            if pix_7 == (255, 0, 0):
                mines_sign[index] = 9
                continue
            elif pix_7 == (255, 255, 255):
                mines_sign[index] = 10
                continue
            elif pix_7 == (192, 192, 192):
                mines_sign[index] = 7
                continue
        elif pix == (128, 128, 128):
            mines_sign[index] = 8
            continue
```

每次调用，即读取一次屏幕上相应位置的像素点，通过像素点判断480个格子显示的是什么



4.找雷和找空函数



```javascript
def solve_a():
    # 中心找空和找雷
    for index, num in enumerate(mines_sign):
        if index in mines_judge:
            if 0 < num < 9:
                booms, blank = 0, 0
                arr = check_8(index)
                if arr:
                    for index_ in arr:
                        if mines_sign[index_] == -1:
                            blank += 1
                        if mines_sign[index_] == 9:
                            booms += 1
                    if booms + blank == num:
                        for index_ in arr:
                            if mines_sign[index_] == -1:
                                click_right(index_)  # 找到是雷
                    elif booms == num and blank != 0:
                        click_mid(index)  # 中心的空格是空


def solve_b():
    # 重叠找空和找雷，需拆分为 找偏移九宫格、对比偏移九宫格空白格+雷格
    for index, num in enumerate(mines_sign):
        if index in mines_judge:
            if 0 < num < 9:
                booms_blank = get_blank_boom(index)  # 存储雷格子、空白格子的列表
                if not booms_blank:
                    continue
                arr_4 = check_4(index)  # arr为index的四个方向偏移序号列表
                for i in arr_4:
                    booms_blank_4 = get_blank_boom(i)  # 存储偏移格子的 雷格子、空白格子的列表
                    if not booms_blank_4:
                        continue
                    result = all(elem in booms_blank_4[1] for elem in booms_blank[1])  # 偏移空格列表包含中心空格列表
                    if result:
                        m = mines_sign[i] - len(booms_blank_4[0])  # 偏移剩余雷数
                        n = mines_sign[index] - len(booms_blank[0])  # 中心剩余雷数
                        if m == n:
                            # 如果 中心剩余雷数 == 偏移剩余雷数，则中心不重叠的地方都是空格
                            for elem in booms_blank_4[1]:
                                if elem not in booms_blank[1]:
                                    # print('重叠找空', elem)
                                    click(elem)
                        if (m - n) >= (len(booms_blank_4[1]) - len(booms_blank[1])) and m != n:
                            # 如果 偏移剩余雷数 - 中心剩余雷数 == 偏移空格数 - 中心空格数，则中心不重叠的地方都是雷
                            for elem in booms_blank_4[1]:
                                if elem not in booms_blank[1]:
                                    # print('重叠找雷', elem)
                                    click_right(elem)
```

5.辅助函数



```javascript
def click_right(index):
    pyautogui.rightClick(mines[index].left + 8, mines[index].top + 8, _pause=False)
    if index in mines_judge:
        mines_judge.remove(index)
    # print('mine 删除 %3d' % index)
    mines_sign[index] = 9


def click_mid(index):
    pyautogui.middleClick(mines[index].left + 8, mines[index].top + 8, _pause=False)
    mines_judge.remove(index)
    # print('mid 删除 %3d' % index)


def click(index):
    # print('left', index)
    pyautogui.click(mines[index].left + 8, mines[index].top + 8, _pause=False)
    if pyautogui.locateOnScreen('10.png', grayscale=True):
        print('BOOM!')
        # sys.exit()
        sleep(1)
        pyautogui.click(mines[15].left + 8, mines[0].top - 32)
        sleep(1)
        run_game()
```

左键、右键、中键点击相应格子的函数，参数index即是相应格子的序号。



如果左键点到雷了则游戏结束，重新开始。



6.其他函数



其他用来辅助算法的函数，不一一列举了







扫雷有时候会出现没法判断的情况，这个时候就是看运气的了，毕竟点开第一个格子不是雷，第二个就炸了的情况经常遇到。



代码链接在下面，需要的可以拿去玩玩啦

[扫雷脚本.rar](assets/扫雷脚本.rar)





作者：神闪避的雪亲王 https://www.bilibili.com/read/cv17598124 出处：bilibili