@RequestMapping

修饰类：提供初步的映射信息，放上级目录，不设置则方法的路径基于根目录

修饰方法：提供详细的映射信息，放下级路径



```javascript
package spring.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class SpringMvcTest {

    @RequestMapping("success")
    public String test1(){
        System.out.println("test1");
        return "success";
    }
}
```

此时通过http://localhost:8080/springm/mvc/success访问/WEB-INF/views/success.jsp



支持ant风格路径

```javascript
@RequestMapping("/*/user") // 匹配/aaa/user、/bbb/user等
@RequestMapping("/**/user") // 匹配/user、/aaa/user、/b/b/b/user等
@RequestMapping("/??user") // 匹配/aauser、/bbuser等
```





支持正则表达式