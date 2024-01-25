接口，被LinkedList实现

| boolean | add(E e)<br>Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available. |
| - | - |
| E | element()<br>Retrieves, but does not remove, the head of this queue. |
| boolean | offer(E e)<br>Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions. |
| E | peek()<br>Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty. |
| E | poll()<br>Retrieves and removes the head of this queue, or returns null if this queue is empty. |
| E | remove()<br>Retrieves and removes the head of this queue. |




```javascript
import java.util.Queue;
import java.util.LinkedList;

public class JavaQueue {
	public static void main(String[] args) {
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		System.out.println(queue.remove());
		queue.offer(3);
		System.out.println(queue.peek());
		System.out.println(queue.element());
		System.out.println(queue.poll());
		System.out.println(queue.peek());
		
	}

}

```

