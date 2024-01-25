新建sofa bundle，web

com.yss.acs.demo



可以查看开发规范4.3资源结构规范



新建组件需要

ds-mapping.xml配置数据源acs、sofa，入门6.3.2.3数据源映射

used-datasource-idx.xml配置数据源





function.xml配置具体功能

应用功能树-组件列表就会显示

可以拉到左边，就可以使用



在授权管理授权，就可以使用

（角色授予页面权限，用户收取角色权限）





web-inf，services.xml注册osgi服务



webcontent，resources，放页面也js等





功能页面

建controller，继承basecontroller

biz继承basecurdbo

vo(实体类)，继承generalVo





公共实体类会放在interface组件里



