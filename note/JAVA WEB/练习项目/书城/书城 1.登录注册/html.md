login.html

```javascript
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        table{
         margin-top: 50px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }
      .in{
         float: right;
      }

    </style>
    <base href="http://localhost:8080/book/" />
</head>
<body>
    <form action="login" method="post">
        <table>
            <tr><td>用户名：<input type="text" class="in" name="username" /></td></tr>
            <tr><td>密码：<input type="password" class="in" name="password" /></td></tr>
            <tr>
                <td>
                    <input type="submit" value="登录" />
                    <input type="button" value="注册" onclick="location.href='pages/user/register.html';" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
```

register.html

```javascript
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>注册</title>
    <style type="text/css">
        table{
         margin-top: 50px;
            margin-left: 35%;
            text-align: center;
        }
      .in{
         float: right;
      }
      .tip{
         text-align: left;
      }
      .red{
         color: red;
      }

    </style>
   <script type="text/javascript" src="/book/js/jquery.min.js"></script>
   <script type="text/javascript">
      $(function(){
         $('.in').focus(function(){
            var $tip = $(this).parent().next('.tip');
            $tip.removeClass("red").text($tip.text().replace("输入错误,",""));
         });
         var usernamePatt = /[a-zA-z]{3,6}/;
         $('input[name="username"]').blur(function(){
            if(!usernamePatt.test(this.value)){
               var $tip = $(this).parent().next('.tip');
               $tip.addClass("red").text("输入错误,"+$tip.text());
            }
         });
         var passwordPatt = /\w{3,6}/;
         $('input[name="password"]').blur(function(){
            if(!passwordPatt.test(this.value)){
               var $tip = $(this).parent().next('.tip');
               $tip.addClass("red").text("输入错误,"+$tip.text());
            }else {
               if($(this).val()==$('#checkPassword').val()){
                  var $tip = $('#checkPassword').parent().next('.tip');
                  $tip.removeClass("red").text($tip.text().replace("输入错误,",""));
               }
            }
         });
         $("#checkPassword").blur(function(){
            if($(this).val()!=$('input[name="password"]').val()){
               var $tip = $(this).parent().next('.tip');
               $tip.addClass("red").text("输入错误,"+$tip.text());
            }
         });
         var emailPatt = /^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\.[a-zA-Z0-9_]+$/;
         $('input[name="email"]').blur(function(){
            if(!emailPatt.test(this.value)){
               var $tip = $(this).parent().next('.tip');
               $tip.addClass("red").text("输入错误,"+$tip.text());
            };
         });

         $('form').submit(function () {
            if($('.in').parent().next('.tip').hasClass('red')||$('.in').filter(function(){if(this.value=="")return true}).length>0){
               alert("请正确填写表单");
               return false;
            }else{

            }
         })
      })
   </script>
   <base href="http://localhost:8080/book/" />
</head>
<body>
    <form action="register" method="post">
      
        <table>
         <tr><td><h2>注册书城网</h2></td></tr>
            <tr>
            <td>用户名：<input type="text" class="in" name="username" /></td>
            <td class="tip">请输入3到6位英文字母</td>
         </tr>
            <tr>
            <td>密码：<input type="password" class="in" name="password" /></td>
            <td class="tip">请输入3到6位字母、数字下划线</td>
         </tr>
         <tr>
            <td>确认密码：<input type="password" class="in" id="checkPassword" /></td>
            <td class="tip">请再次输入相同的密码</td>
         </tr>
         <tr>
            <td>电子邮件：<input type="text" class="in" name="email" /></td>
            <td class="tip">邮箱地址如abc@163.com</td>
         </tr>
            <tr>
                <td>
                    <input type="submit" value="注册" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
```

