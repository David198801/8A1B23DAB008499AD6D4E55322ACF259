process：instead of CPU

exec：instead of memery

files: instead of disk block



OS should be defensive

app cannot crush the 

app cannnot break out of isolation



must be strong isolation between apps and OS

tipical : hardware support

2 kinds:

1.user/kernal mode

2.virtual memory (page table)





user/kernal mode

kernal mode:CPU can execute privileged instructions (like setting up page tables,disabling clock interrupts)

user mode: only unprivileged instructions. (like add, sub, jr )



CPU provide virtual memory

page table map virtual address to physical address

basic idea:give every process it's own page table

strong memory isolation





entering kernel

ecall <n> ,the number basically is the system call number



例如调用fork()，并不是直接调用内核的fork带啊吗，实际上是调用ecall fork()的system number，进行用户态/内核态的切换



kernel = trusted computing base,tcb

--kernel must have no bug

--kernel must treat process as malicious



宏内核 monolithic kernel design：系统所有服务实现在内核中

缺点：内核代码量大，容易出现bug

有点：各个模块紧密结合，性能好



微内核 micro kernel design： 尽量减少内核代码，一般包含IPC、少量VM支持(基本只有页表相关的东西)、复用CPU

通常工作方式：例如shell通过IPC发送消息给内核，内核发送给文件系统程序，文件系统处理完成再发送给内核，内核发给shell，调用次数多





xv6代码

分为两个部分 user，kernel

xv6为宏内核，整个内核编译为一个二进制文件：kernel

user为用户态应用程序

还有一个程序mkfs，创建一个空文件系统镜像



编译过程

proc.c -> proc.S （risc-v汇编）-> proc.O (risc二进制) -> ld链接 -> 生成kernel

pipe.c        ->          pipe.S         ->            pipe.O  ->



makefile还配置生成了kernel.asm，内核的反汇编代码