导出excel等操作时poi报错java.io.IOException: No such file or directory



原因：linux临时文件目录/tmp被清理，导致poi找不到临时目录/tmp/poifiles

 

修复：指定java.io.tmpdir为其他路径，可以添加启动参数-Djava.io.tmpdir=/xxx，或者使用System.setProperty("java.io.tmpdir", "/path/to/your/temp/dir");



was修复:

服务器 -> WebSphere Application Server -> 选择对应服务器 -> Java和进程管理 -> 进程定义 -> Java虚拟机 -> 通用JVM参数中增加【  -Djava.io.tmpdir=/home/was/tmp】 -> 确定  -> 保存配置  -> 重启was