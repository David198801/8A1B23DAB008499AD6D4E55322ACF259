题目描述

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。

n≤39

JAVA

```javascript
public static int Fibonacci(int n) {
    if(n==0||n==1){
        return n;
    }
    
    /* n从0开始，输入n则求0~n号项(从0开始的斐波那契数列)，共n+1项
     * 
     * while(n>0){
     *     n--;
     * }
     * 执行n次
     * 
     * g += f,即g = f(n) = f(n-1) + f(n-2)
     * f = g-f,即f = f(n) - f(n-2) = f(n-1)
     * 下一次循环，则g+f=f(n+1) = f(n) + f(n-1)
     * 
     * 0号项、1号项已定义，求2~n号项，共需执行n-1次
     * 故执行n次则返回f
     * 
     * 
     * */
    int f = 0,g = 1;
    while(n>0){
        g += f;
        f = g-f;
        n--;
    }
    return f;
}
```





c++动态规划版

```javascript
class Solution {
public:
    int Fibonacci(int n) {
        if(n==0||n==1){
            return n;
        }
        int f = 0, g = 1;
        while(n--) {
            g += f;
            f = g - f;
        }
        return f;
    }
};
```

