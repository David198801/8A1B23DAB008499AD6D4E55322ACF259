void的handler方法如

void doDelete()

会默认按照url+前后缀去寻找jsp视图，如/xxx/doDelete会去寻找/xxx/doDelete.jsp

响应空的内容的方法

1.不用void，返回一个ResponseEntity<Void>类型，使用常量

https://stackoverflow.com/questions/26550124/spring-returning-empty-http-responses-with-responseentityvoid-doesnt-work

```javascript
ResponseEntity<Void> doDelete(){
    //...
    return ResponseEntity.ok().build();//响应200
    //return ResponseEntity.noContent().build();//响应204（成功，没有内容）
    //return ResponseEntity.notFound().build();//响应404
}
```

2.给一个@ResponseBody，响应200

```javascript
@RequestMapping("/doDelete")
@ResponseBody
void doDelete(){}
```

3.给一个@RestController，包括@Controller和@ResponseBody



其他方法：传入servlet的response进行响应

https://blog.csdn.net/yh_zeng2/article/details/75136614