

```javascript
public class MyRunnable implements Runnable{
	
	public boolean flag;

	@Override
	public void run() {
		flag = true;
		for (int i = 0; flag; i++) {
			System.out.println(i);
		}
	}

}

```



```javascript
public class Test {
    
	public static void main(String[] args) {
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(mr);
		t1.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mr.flag = false;
	}

}
```

