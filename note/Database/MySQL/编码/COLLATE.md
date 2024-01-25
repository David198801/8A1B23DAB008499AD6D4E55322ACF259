COLLATE即排序规则





默认

_general，不影响中文，部分外文字符顺序等价，推荐_unicode顺序

_ci，Case Insensitive，大小写无关

其他

_unicode，unicode顺序

_ai，accent insensitive，发音无关



COLLATE会影响到ORDER BY语句的顺序，会影响到WHERE条件中大于小于号筛选出来的结果，会影响**DISTINCT**、**GROUP BY**、**HAVING**语句的查询结果。

另外，mysql建索引的时候，如果索引列是字符类型，也会影响索引创建，只不过这种影响我们感知不到。总之，凡是涉及到字符类型比较或排序的地方，都会和COLLATE有关。



设置范围

向上级继承。

COLLATE可以在示例级别、库级别、表级别、列级别、以及SQL查询语句中指定。实例级别的COLLATE设置就是mysql配置文件或启动指令中的collation_connection系统变量。