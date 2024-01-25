前端发送json字符串

```javascript
<input type="button" id="testSendJson" value="发送json"/>
<script>
    $(function () {
        $('#testSendJson').click(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/mvc/testReceiveJson",
                type:"POST",
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({id:1,name:"abcdde"})
            })
        })
    })
</script>
```

后端可以直接以字符串接收，也可以转为map、list、实体类

https://www.cnblogs.com/zzsuje/articles/10329948.html

```javascript
@RequestMapping("/testReceiveJson")
public String testReceiveJson(@RequestBody String jsonStr){
    System.out.println(jsonStr);//{"id":1,"name":"abcdde"}
    return "success";
}
```

