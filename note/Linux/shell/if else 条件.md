if else

fi

if 语句语法格式：

if condition
then
    command1 
    command2
    ...
    commandN 
fi

写成一行（适用于终端命令提示符）：

if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi

末尾的 fi 就是 if 倒过来拼写，后面还会遇到类似的。

if else

if else 语法格式：

if condition
then
    command1 
    command2
    ...
    commandN
else
    command
fi

if else-if else

if else-if else 语法格式：

if condition1
then
    command1
elif condition2 
then 
    command2
else
    commandN
fi

以下实例判断两个变量是否相等：

实例

a=10

b=20

if [ $a == $b ]

then

   echo "a 等于 b"

elif [ $a -gt $b ]

then

   echo "a 大于 b"

elif [ $a -lt $b ]

then

   echo "a 小于 b"

else

   echo "没有符合的条件"

fi

输出结果：

a 小于 b

if else 语句经常与 test 命令结合使用，如下所示：

实例

num1=$[2*3]

num2=$[1+5]

if test $[num1] -eq $[num2]

then

    echo '两个数字相等!'

else

    echo '两个数字不相等!'

fi

输出结果：

两个数字相等!

case ... esac

case ... esac 为多选择语句，与其他语言中的 switch ... case 语句类似，是一种多分枝选择结构，每个 case 分支用右圆括号开始，用两个分号 ;; 表示 break，即执行结束，跳出整个 case ... esac 语句，esac（就是 case 反过来）作为结束标记。

可以用 case 语句匹配一个值与一个模式，如果匹配成功，执行相匹配的命令。

case ... esac 语法格式如下：

case 值 in
模式1)
    command1
    command2
    ...
    commandN
    ;;
模式2）
    command1
    command2
    ...
    commandN
    ;;
esac

case 工作方式如上所示，取值后面必须为单词 in，每一模式必须以右括号结束。取值可以为变量或常数，匹配发现取值符合某一模式后，其间所有命令开始执行直至 ;;。

取值将检测匹配的每一个模式。一旦模式匹配，则执行完匹配模式相应命令后不再继续其他模式。如果无一匹配模式，使用星号 * 捕获该值，再执行后面的命令。

下面的脚本提示输入 1 到 4，与每一种模式进行匹配：

实例

echo '输入 1 到 4 之间的数字:'

echo '你输入的数字为:'

read aNum

case $aNum in

    1)  echo '你选择了 1'

    ;;

    2)  echo '你选择了 2'

    ;;

    3)  echo '你选择了 3'

    ;;

    4)  echo '你选择了 4'

    ;;

    *)  echo '你没有输入 1 到 4 之间的数字'

    ;;

esac

输入不同的内容，会有不同的结果，例如：

输入 1 到 4 之间的数字:
你输入的数字为:
3
你选择了 3

下面的脚本匹配字符串：

实例

#!/bin/sh



site="runoob"



case "$site" in

   "runoob") echo "菜鸟教程"

   ;;

   "google") echo "Google 搜索"

   ;;

   "taobao") echo "淘宝网"

   ;;

esac

输出结果为：

菜鸟教程



