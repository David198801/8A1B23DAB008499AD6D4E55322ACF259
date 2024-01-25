输出：

进入方法A

方法A的finally

制造异常

进入方法B

方法B的finally



内层的finally在外层catch之前执行

```javascript
public class RuntimeExceptionTest {
    static void methodA(){
        try {
            System.out.println("进入方法A");
            throw new RuntimeException("制造异常");
        }finally {
            System.out.println("方法A的finally");
        }
    }

    static void methodB(){
        try {
            System.out.println("进入方法B");
            return;
        }finally {
            System.out.println("方法B的finally");
        }
    }

    public static void main(String[] args) {
        try {
            methodA();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        methodB();
    }
}
```

