@Import写在配置类或组件类上

可以导入自定义的组件，或者spring boot默认创建的组件

```javascript
@Import({User.class, DBHelper.class})
```

会自动使用无参构造器创建传入类型的对象，name为全类名



```javascript
String[] names = run.getBeanNamesForType(User.class);
for (String name : names) {
    System.out.println(name);
}

String[] names2 = run.getBeanNamesForType(DBHelper.class);
for (String name : names2) {
    System.out.println(name);
}
// com.lzb.boot.pojo.User
// user，自己创建的user
// ch.qos.logback.core.db.DBHelper
```

