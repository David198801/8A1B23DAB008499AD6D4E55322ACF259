for 循环

与其他编程语言类似，Shell支持for循环。

for循环一般格式为：

for var in item1 item2 ... itemN
do
    command1
    command2
    ...
    commandN
done

写成一行：

for var in item1 item2 ... itemN; do command1; command2… done;

当变量值在列表里，for 循环即执行一次所有命令，使用变量名获取列表中的当前取值。命令可为任何有效的 shell 命令和语句。in 列表可以包含替换、字符串和文件名。

in列表是可选的，如果不用它，for循环使用命令行的位置参数。

例如，顺序输出当前列表中的数字：

实例

for loop in 1 2 3 4 5

do

    echo "The value is: $loop"

done

输出结果：

The value is: 1
The value is: 2
The value is: 3
The value is: 4
The value is: 5

顺序输出字符串中的字符：

#!/bin/bash

for str in This is a string
do
    echo $str
done

输出结果：

This
is
a
string

---

while 语句

while 循环用于不断执行一系列命令，也用于从输入文件中读取数据。其语法格式为：

while condition
do
    command
done

以下是一个基本的 while 循环，测试条件是：如果 int 小于等于 5，那么条件返回真。int 从 1 开始，每次循环处理时，int 加 1。运行上述脚本，返回数字 1 到 5，然后终止。

实例

#!/bin/bash

int=1

while(( $int<=5 ))

do

    echo $int

    let "int++"

done

运行脚本，输出：

1
2
3
4
5

以上实例使用了 Bash let 命令，它用于执行一个或多个表达式，变量计算中不需要加上 $ 来表示变量，具体可查阅：Bash let 命令

。

while循环可用于读取键盘信息。下面的例子中，输入信息被设置为变量FILM，按<Ctrl-D>结束循环。

实例

echo '按下 <CTRL-D> 退出'

echo -n '输入你最喜欢的网站名: '

while read FILM

do

    echo "是的！$FILM 是一个好网站"

done

运行脚本，输出类似下面：

按下 <CTRL-D> 退出
输入你最喜欢的网站名:菜鸟教程
是的！菜鸟教程 是一个好网站

无限循环

无限循环语法格式：

while :
do
    command
done

或者

while true
do
    command
done

或者

for (( ; ; ))

---

until 循环

until 循环执行一系列命令直至条件为 true 时停止。

until 循环与 while 循环在处理方式上刚好相反。

一般 while 循环优于 until 循环，但在某些时候—也只是极少数情况下，until 循环更加有用。

until 语法格式:

until condition
do
    command
done

condition 一般为条件表达式，如果返回值为 false，则继续执行循环体内的语句，否则跳出循环。

以下实例我们使用 until 命令来输出 0 ~ 9 的数字：

实例

#!/bin/bash



a=0



until [ ! $a -lt 10 ]

do

   echo $a

   a=`expr $a + 1`

done

运行结果：

输出结果为：

0
1
2
3
4
5
6
7
8
9



跳出循环

在循环过程中，有时候需要在未达到循环结束条件时强制跳出循环，Shell使用两个命令来实现该功能：break和continue。

break命令

break命令允许跳出所有循环（终止执行后面的所有循环）。

下面的例子中，脚本进入死循环直至用户输入数字大于5。要跳出这个循环，返回到shell提示符下，需要使用break命令。

实例

#!/bin/bash

while :

do

    echo -n "输入 1 到 5 之间的数字:"

    read aNum

    case $aNum in

        1|2|3|4|5) echo "你输入的数字为 $aNum!"

        ;;

        *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"

            break

        ;;

    esac

done

执行以上代码，输出结果为：

输入 1 到 5 之间的数字:3
你输入的数字为 3!
输入 1 到 5 之间的数字:7
你输入的数字不是 1 到 5 之间的! 游戏结束

continue

continue命令与break命令类似，只有一点差别，它不会跳出所有循环，仅仅跳出当前循环。

对上面的例子进行修改：

实例

#!/bin/bash

while :

do

    echo -n "输入 1 到 5 之间的数字: "

    read aNum

    case $aNum in

        1|2|3|4|5) echo "你输入的数字为 $aNum!"

        ;;

        *) echo "你输入的数字不是 1 到 5 之间的!"

            continue

            echo "游戏结束"

        ;;

    esac

done

运行代码发现，当输入大于5的数字时，该例中的循环不会结束，语句 echo "游戏结束" 永远不会被执行。





shell 中的 for 循环不仅可以用文章所述的方法。

对于习惯其他语言 for 循环的朋友来说可能有点别扭。

for((assignment;condition:next));do
    command_1;
    command_2;
    commond_..;
done;

如上所示，这里的 for 循环与 C 中的相似，但并不完全相同。

通常情况下 shell 变量调用需要加 $,但是 for 的 (()) 中不需要,下面来看一个例子：

#!/bin/bash
for((i=1;i<=5;i++));do
    echo "这是第 $i 次调用";
done;

执行结果：

这是第1次调用
这是第2次调用
这是第3次调用
这是第4次调用
这是第5次调用

与 C 中相似，赋值和下一步执行可以放到代码之前循环语句之中执行，这里要注意一点：如果要在循环体中进行 for 中的 next 操作，记得变量要加 $，不然程序会变成死循环。





从 ubuntu 6.10 开始，ubuntu 就将先前默认的 bash shell 更换成了dash shell，其表现为 /bin/sh 链接倒了 /bin/dash 而不是传统的 /bin/bash。

可以通过 ls -l /bin/*sh 命令看到：

![](assets/for%20while%20until%20循环_image_0.jpeg)

所以在使用 sh 命令执行脚本的时候实际使用的是 dash，而 dash 不支持这种 C 语言格式的 for 循环写法。

解决方法：使用 bash 代替 sh 运行脚本：

bash test.sh



