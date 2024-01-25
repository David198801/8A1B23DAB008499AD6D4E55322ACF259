https://zhuanlan.zhihu.com/p/490307409



昨天无意间在 github 上刷到了 Rui Ueyama 大大的 chibicc，简单学习了一下后，惊为天人。整体的代码结构非常清晰，代码质量极高，所以开心地来写篇文安利一下。

本文主要会简单介绍一下 chibicc，并整理一下 chibicc 的编译流程中的一些关键函数，方便大家读源码。不过，由于这个项目写的非常清晰，对于编译器的基本原理有所了解的朋友，完全可以忽略后面的内容，直接去看源码：

https://github.com/rui314/chibicc​github.com/rui314/chibicc

chibicc 是一个实现了大多数 C11 特性的 C 编译器，整体不到一万行的情况下，可以完成自举，编译 Git, SQLite, libpng 等大型 C 项目，且编译结果可以顺利通过这些库的全部测试。这个项目是 Rui Ueyama 为自己正在写作的 C 编译器相关的书写的参考实现，所以他不仅对最终代码的可读性进行了把控，而是保证了每个 commit 的内容都相对独立，大小适中，且 commit log 写的非常清晰，读者可以随着 commit log 看到项目是如何从只能输出一个整数的小程序，到最终完成自举的编译器的，下面为摘出的最初的几个 commit 的日志：

$ git log --reverse
commit 0522e2d77e3ab82d3b80a5be8dbbdc8d4180561c
Author: Rui Ueyama <ruiu@cs.stanford.edu>
Date:   Sat Aug 3 11:29:50 2019 +0900

    Compile an integer to an exectuable that exits with the given number

commit bf7081fba7d8c6b1cd8a12eb329697a5481c604e
Author: Rui Ueyama <ruiu@cs.stanford.edu>
Date:   Sat Aug 3 12:20:08 2019 +0900

    Add + and - operators

commit a1ab0ff26f23c82f15180051204eeb6279747c9a
Author: Rui Ueyama <ruiu@cs.stanford.edu>
Date:   Sat Aug 3 12:36:06 2019 +0900

    Add a tokenizer to allow space characters between tokens

commit cc5a6d978144bda90220bd10866c4fd908d07546
Author: Rui Ueyama <ruiu@cs.stanford.edu>
Date:   Sat Aug 3 15:02:08 2019 +0900

    Improve error message
    
    Now, chibicc can print out an error message with an error location
    like this:
    
      $ ./chibicc 1+foo
      1+foo
        ^ expected a number
...

而且，作者保证了每个 commit 都 bugfree，所以每个 commit 都可以自行编译测试：

When I find a bug in this compiler, I go back to the original commit that introduced the bug and rewrite the commit history as if there were no such bug from the beginning. This is an unusual way of fixing bugs, but as a part of a book, it is important to keep every commit bug-free.

从整体架构上，chibicc 主要的内容是从 parse 出的 AST 直接进行 codegen 生成汇编代码，没有各种优化 pass，适合用来学习基本的汇编知识。生成汇编后，chibicc 采用 as 来编译汇编，用 ld 进行链接。考虑到作者是链接器大牛，所以也可以顺便学一下链接器的那些 flag 应该怎么加。

下面我们来稍微深入一下代码。chibicc 进行编译的流程如下：

- tokenize(File *file)：lexing；将文件从文本转为 token，输出是一个 token list；

- preprocess(Token *tok)：通过 3 次遍历 token list 来完成预处理：识别并展开各类宏、对数字和 keyword token 求值、连接相连的字符串；

- parse(Token *tok)：由顶至下对 token list 进行解析，所有的函数与全局变量的 AST 都存储在全局变量 globals 中，函数的返回值也是这个全局变量 globals。在 parse 的最后还会通过标记的方式，以 globals 为根，去除所有不会被访问的代码（简化版 DCE）；

- codegen(Obj *prog, FILE *out)：代码生成。这里为了简化，所有变量都会放在栈上。具体操作是，首先计算好所有局部变量在栈上的位置，然后分别去生成静态的 .data、.bss 等部分和表示代码的 .text 部分，其中 .text 部分就是通过遍历 AST 完成的，具体可以看 gen_stmt 函数，通过 fprintf 把对应的汇编指令输出到临时文件里；

- assemble(char *input, char *output)：调用 as 汇编器生成二进制文件；

- run_linker(StringArray *inputs, char *output)：调用 ld 把生成的二进制文件链接成一个文件。

是不是很简单呢？还不快 git clone 走起~ （小窍门，在 github 上点 "." 可以直接进入网页版 vscode，所以也可以不 clone 到本地...）















hcc_c

https://github.com/JettHuang/hcc_c