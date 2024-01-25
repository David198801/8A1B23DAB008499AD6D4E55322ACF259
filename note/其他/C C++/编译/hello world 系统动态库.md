win

```shell
# ldd a
        ntdll.dll => /c/Windows/SYSTEM32/ntdll.dll (0x772f0000)
        kernel32.dll => /c/Windows/system32/kernel32.dll (0x770d0000)
        KERNELBASE.dll => /c/Windows/system32/KERNELBASE.dll (0x7fefd2c0000)
        msvcrt.dll => /c/Windows/system32/msvcrt.dll (0x7fefe520000)
```

centos

```shell
# ldd a.out
        linux-vdso.so.1 =>  (0x00007ffdbf3cd000)
        libc.so.6 => /lib64/libc.so.6 (0x00007f637962f000)
        /lib64/ld-linux-x86-64.so.2 (0x00007f63799fd000)
```

