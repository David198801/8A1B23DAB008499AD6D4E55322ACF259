

```javascript
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>
        <script type="text/javascript" src="./vue.js"></script>
    </head>
    <body>
        <div id="box">
            <form>
                <table border="1px">
                    <tr>
                        <td>
                            用户名
                        </td>
                        <td>
                            <input type="text" name="username" value="" v-model="username" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            年级
                        </td>
                        <td>
                            <select name="grade" v-model="grade">
                                <option>一年级</option>
                                <option>二年级</option>
                                <option>三年级</option>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            爱好
                        </td>
                        <td>
                            <input type="checkbox" name="hobby" value="游泳" v-model="hobby" />
                            <input type="checkbox" name="hobby" value="跑步" v-model="hobby" />
                            <input type="checkbox" name="hobby" value="健身" v-model="hobby" />
                        </td>
                    </tr>
                </table>
            </form>
            <hr />
            <p>用户名 {{username}}</p>
            <p>年级 {{grade}}</p>
            <p>爱好 {{hobby}}</p>
        </div>
    </body>
    <script>
        var v = new Vue({
            el: '#box',
            data: {
                username: '',
                grade: '',
                hobby: []
            }
        })
    </script>
</html>

```







v-model绑定computed数据

方法1.设置set

方法2.使用onchange事件控制

https://blog.csdn.net/lvonve/article/details/111317526