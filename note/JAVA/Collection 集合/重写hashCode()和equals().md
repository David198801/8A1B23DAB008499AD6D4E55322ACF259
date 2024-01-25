IDE自动生成



《Effective Java》中提出了一种简单通用的hashCode算法

A、初始化一个整形变量，为此变量赋予一个非零的常数值，比如int result = 17;

B、选取equals方法中用于比较的所有域（之所以只选择equals()中使用的域，是为了保证上述原则的第1条），然后针对每个域的属性进行计算：

1. 如果是boolean值，则计算 f ? 1:0；

1. 如果是byte\char\short\int,则计算 (int)f；

1. 如果是long值，则计算 (int)(f ^ (f >>> 32))；

1. 如果是float值，则计算 Float.floatToIntBits(f)；

1. 如果是double值，则计算 Double.doubleToLongBits(f)，然后返回的结果是long,再用规则(3)去处理long,得到int；

1. 如果是对象应用，如果equals方法中采取递归调用的比较方式，那么hashCode中同样采取递归调用hashCode的方式。否则需要为这个域计算一个范式，比如当这个域的值为null的时候，那么hashCode 值为0；

1. 如果是数组，那么需要为每个元素当做单独的域来处理。java.util.Arrays.hashCode 方法包含了8种基本类型数组和引用数组的hashCode计算，算法同上。

C、最后，把每个域的散列码合并到对象的哈希码中。



```javascript
public class Person {
    private String name;
    private int age;
    private boolean gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                gender == person.gender &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + getName().hashCode();
        hash = hash * 31 + getAge();
        return hash;
    }
```





lombok项目提供注解功能

1.添加lombok项目的依赖

```javascript
<dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.16.20</version>
    </dependency>
```

2.加注解：@Data 和 @EqualsAndHashCode

```javascript
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"score","time"})
public class EqualsDemo {
    private String name;
    private int age;
    private boolean gender;
    private float score;
    private String time;
}
```

