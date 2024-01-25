现在我们要查找人口数比欧洲所有人口数都多的国家名称，那么代码如下：

```javascript
SELECT `name` FROM world
WHERE population > ALL(SELECT population FROM world WHERE continent = 'Europe');
```

其中子查询语句的结果是欧洲所有国家人口数的集合，条件子句的含义就是人口数要大于该集合中的所有值，该条件才能成立。

反之如果将all变成any，那么只要大于结果集中任意的一个值条件就成立。

SOME和ANY的用法一致。