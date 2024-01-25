https://www.zhihu.com/question/454347340



ClassLoader在加载类的时候，最终会以类的名字（字符串）获取一个锁对象（Object），来避免并发问题，这个锁对象（Object）是存储在一个ConcurrentHashMap里面的，key是类名。