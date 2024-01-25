tomcat在使用spring框架或其他情况下中文乱码

需要统一编码为UTF-8



给tomcat加上环境变量

JAVA_OPTS，值为-Dfile.encoding=UTF-8，传递给jdk

JAVA_TOOL_OPTIONS，值为-Dfile.encoding=UTF-8



idea启动参数也加上-Dfile.encoding=UTF-8



tomcat9本身的提示输出编码是UTF-8(tomcat7是gbk)，需要idea控制台编码为UTF-8，但tomcat运行spring时System.out输出编码又为gbk，启动脚本前需要给一个环境变量JAVA_OPTS传参给jdk