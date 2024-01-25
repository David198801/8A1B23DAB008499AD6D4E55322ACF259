题目描述

用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型

```javascript
import java.util.Stack;

public class Test01 {

	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
    	if(stack2.empty()) {
    		while(!stack1.empty()) {
    			stack2.push(stack1.pop());
    		}
    		
    	}
    	Integer a = stack2.pop();
    	System.out.print(a);
		return a;
    }

}
```

将stack2作为缓冲区