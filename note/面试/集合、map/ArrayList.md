https://blog.csdn.net/zymx14/article/details/78324464

默认容量10



add()时，如果容量不足(size+1大于elementData.length)，则调用grow()扩容,大小为oldCapacity + oldCapacity / 2，即1.5倍



ArrayList线程不同步