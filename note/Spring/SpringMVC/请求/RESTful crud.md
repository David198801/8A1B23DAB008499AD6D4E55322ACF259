1.显示所有格员工

GET /users

```javascript
@GetMapping("/users")
public String users(Map<String,Object> map){
    List<User> users = userService.getAllUsers();
    map.put("users", users);
    return "users";
}
```



2.添加员工

POST /user

完成后重定向到显示

```javascript
@PostMapping("/user")
public String addUser(User user){
    userService.addUser(user);
    return "redirect:/users";
}

@GetMapping("/addUser")
public String addUserPage(){
    return "addUser";
}
```



3.删除

DELETE /user/{id}

```javascript
@DeleteMapping("/user/{id}")
public String deleteUser(@PathVariable("id") Integer id){
    userService.deleteUserById(id);
    return "redirect:/users";
}
```



```javascript
<a href="####" id="deleteUser">删除User28</a>
<script>
    $(function () {
        $('#deleteUser').click(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/28",
                type:"DELETE"
            })
        })
    })
</script>
```

删除多个

https://www.zhihu.com/question/65356825



4.修改，要求lastName不可修改

PUT /user/{id}



