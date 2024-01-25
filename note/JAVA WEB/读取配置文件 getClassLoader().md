可以返回WebAppClassLoader的资源

JdbcUtils.class.getResourceAsStream()，默认在类所在的目录下读取

JdbcUtils.class.getClassLoader().getResourceAsStream()，默认在classpath下读取

Thread.currentThread().getContextClassLoader().getResourceAsStream()，默认在classpath下读取



通过ApplicationClassLoader,只加载java classpath下的文件，无法获取tomcat classes下的文件

ClassLoader.getSystemClassLoader().getResourceAsStream()，默认在classpath下读取

ClassLoader.getSystemResourceAsStream()，默认在classpath下读取