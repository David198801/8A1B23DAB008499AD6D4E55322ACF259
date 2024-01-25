@test函数是不会等待子线程运行结束的,只要@test函数执行结束就立即结束.

如果是自己写的子线程，可以使用join或者CountDownLatch等待

https://blog.csdn.net/cristianoxm/article/details/120213428