The import org.junit.Test conflicts with a type defined in the same file



原因一：常规原因，导入的jar包相冲突

 

原因二：狗血原因，写junit测试的java类名为Test.java

 

一定要用Test.java的话只能在方法上加

```javascript
@org.junit.Test
```

 而不能单纯加

```javascript
@Test
```

