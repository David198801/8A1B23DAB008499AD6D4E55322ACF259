Result Maps collection does not contain value for

原因：某个xml中写了不存在的resultMap

mybatis会为所有的mapper创建代理，即使要执行的mapper没错，其他mapper的错误也会抛出这个异常