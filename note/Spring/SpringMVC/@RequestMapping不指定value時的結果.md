今天看spring boot项目代码的时候发现一个情况，就是@RequestMapping里面不设置value的值。以前没见过，所以就去查了下资料，发现这种用法并不多见，并没直接搜到不指定value会怎么样。

 当我们不指定value的时候就，在一个提交方法只有一个的时候并不会报错，也就是如下

```javascript
@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
public void myTest(@RequestParam String name) {
    System.out.println("sss");
}

@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
public void myTest2(@RequestBody Book book) {
    System.out.println("232");
}
```

这种POST、GET各只有一个的是没错的。

访问起来的时候，我开始怀疑是会不会是可以以方法名访问，但是尝试之后并不会，而只会以根的形式访问，如上就是通过

localhost:8080/classMapping(我拿来指代一下类的mapping)，因为这个是get请求所以可以访问到myTest()

