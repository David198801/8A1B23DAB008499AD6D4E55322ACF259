1.可重复注解

注解可以重复使用，定义不同参数

需要定义@MyAnnotations

```javascript
@MyAnnotations(){
    MyAnnotation[] value();
}
```

1.8之前使用传入数组代替。

```javascript
@MyAnnotations({@MyAnnotation("a"),@MyAnnotation("b")})
```



jdk8中使用@Repeatable(MyAnnotations.class)，两者的@Retention、@Target、是否@Inherited必须一致

```javascript
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation(){
}
```



```javascript
@MyAnnotation("a")
@MyAnnotation("b")
```





2.类型注解

@Target新增两种类型

@Target(ElementType.TYPE_PARAMETER)，注解能写在类型变量的声明语句中

@Target(ElementType.TYPE_USE)，注解能写在使用类型的任何语句中



@Target(ElementType.TYPE_PARAMETER)

```javascript
public class TypeParameterClass<@TypeParameterAnnotation T> {
    public <@TypeParameterAnnotation U> T foo(T t) {
        return null;
    }   
}
```



@Target(ElementType.TYPE_USE)

```javascript
int num = (@MyAnnotation)int 10L;

public void show() throws @MyAnnotation RuntimeException{
    
}
```

能标注任何类型名称，包括上面这个（ElementType.TYPE_PARAMETER的），例子如下：

```javascript
public class TestTypeUse {
 
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TypeUseAnnotation {
         
    }
     
    public static @TypeUseAnnotation class TypeUseClass<@TypeUseAnnotation T> extends @TypeUseAnnotation Object {
        public void foo(@TypeUseAnnotation T t) throws @TypeUseAnnotation Exception {
             
        }
    }
     
    // 如下注解的使用都是合法的
    @SuppressWarnings({ "rawtypes", "unused", "resource" })
    public static void main(String[] args) throws Exception {
        TypeUseClass<@TypeUseAnnotation String> typeUseClass = new @TypeUseAnnotation TypeUseClass<>();
        typeUseClass.foo("");
        List<@TypeUseAnnotation Comparable> list1 = new ArrayList<>();
        List<? extends Comparable> list2 = new ArrayList<@TypeUseAnnotation Comparable>();
        @TypeUseAnnotation String text = (@TypeUseAnnotation String)new Object();
        java.util. @TypeUseAnnotation Scanner console = new java.util.@TypeUseAnnotation Scanner(System.in);
    }
}
```

