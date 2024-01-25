https://www.cnblogs.com/zhuyeshen/p/10956822.html

https://www.imooc.com/article/295838



迭代器或foreach中，add/remove/put会出现ConcurrentModificationException

原因是迭代器的expectedModCount与modCount不一致

设计上的原因应该是迭代器中不应该去修改

```javascript
public static void main(String[] args) {
	
	List list = new ArrayList();
	list.add(1);
	list.add(2);
	
	
	for (Object object : list) {
		if(object.equals(2)){
			list.remove(object);
		}
	}
	
	for (Object object : list) {
		if(object.equals(2)){
			list.remove(object);
		}
	}
	
}
```

Exception in thread "main" java.util.ConcurrentModificationException

	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:819)

	at java.util.ArrayList$Itr.next(ArrayList.java:791)

	at temp.Test.main(Test.java:18)



解决：

1.使用普通for循环，但是会改变size，用固定变量判断可能越界，用i<list.size()在remove时会漏掉后面一个元素，适用于情况：1.add 2.一次remove后break 3.倒序remove

2.对于remove()，可以使用迭代器的remove()，it.remove()

3.建一个新的集合，遍历结束后再add/remove()

4.对于Collection，使用removeIf()，要求jdk1.8

```javascript
public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2"); 
    list.removeIf("2"::equals);
}
```

5.对于多线程，需要在使用到迭代器的地方同步或使用CopyOnWriteArrayList