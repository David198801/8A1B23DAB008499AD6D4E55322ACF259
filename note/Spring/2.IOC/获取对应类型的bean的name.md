

```javascript
String[] beanNamesForType = context.getBeanNamesForType(Person.class);
for (String s : beanNamesForType) {
    System.out.println(s);//person1
}
```

