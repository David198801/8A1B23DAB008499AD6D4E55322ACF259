

```javascript
@Test
public void test1(){
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
        System.out.println(beanDefinitionName);
    }
    /*
    springConfig
    personDao
    personService
    person1
    */
}
```

