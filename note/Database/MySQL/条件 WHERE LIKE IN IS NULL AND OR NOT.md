条件运算符

| 运算符 | 含义 | 范例 | 结果 |
| - | - | - | - |
| = | 等于 | 5=6 | FALSE |
| &lt;&gt; 或 != | 不等于 | 5!=6 | TRUE |
| &gt; | 大于 | 5&gt;6 | FALSE |
| &lt; | 小于 | 5&lt;6 | TRUE |
| &gt;= | 大于等于 | 5&gt;=6 | FALSE |
| &lt;= | 小于等于 | 5&lt;=6 | TRUE |
| BETWEEN<br>AND | 在某个范围之间 | BETWEEN 5 AND 10   | AND 或 &amp;&amp; |
| 并且 | 5&gt;1 AND 1&gt;2 | FALSE | OR 或 || |
| 或 | 5&gt;1 OR 1&gt;2 | TRUE | NOT 或 ! |
| 非 | &lt;=&gt; | 安全等于 | 既可以判断数值，也可以判断NULL |




```javascript
SELECT * FROM student WHERE id=1;
SELECT * FROM student WHERE id = '1'; -- 同上
-- 字符串比较用=
SELECT * FROM student WHERE pwd='123456';
SELECT * FROM student WHERE id<>1;
SELECT * FROM student WHERE id>5;
SELECT * FROM student WHERE id<=4;

SELECT * FROM student WHERE id<>1 AND birthday IS NULL;
SELECT * FROM student WHERE id<>1 OR birthday IS NULL;
SELECT * FROM student WHERE birthday IS NOT NULL;
SELECT * FROM student WHERE NOT birthday IS NULL; -- 同上
SELECT * FROM student WHERE NOT (id<>1 AND birthday IS NULL);

-- 使用BETWEEN 
SELECT * FROM student WHERE id BETWEEN 1 AND 5;
SELECT * FROM student WHERE id NOT BETWEEN 1 AND 5;

-- 顺序不能颠倒
-- 等价于WHERE id>=5 AND id<=1;
SELECT * FROM student WHERE id BETWEEN 5 AND 1; -- 空表

-- 模糊条件
/*
LIKE使用通配符
% 任意字符，类似*
_ 单个字符，类似?
*/

SELECT * FROM student WHERE pwd LIKE '15%';
SELECT * FROM student WHERE pwd LIKE '15_';

-- 转义
SELECT * FROM student WHERE pwd LIKE '_\_a%'; -- \_视为_
-- 自定义转义字符
SELECT * FROM student WHERE pwd LIKE '_$_a%' ESCAPE '$';

-- IN
-- 括号中值类型必须相同或兼容
SELECT * FROM student WHERE id IN (1,2,999,9999);
SELECT * FROM student WHERE id IN (1,'2',999,9999); -- 同上
SELECT * FROM student WHERE id NOT IN (1,2,999,9999);

-- 安全等于，既可以判断数值，也可以判断NULL
SELECT * FROM student WHERE id <=> '1';
SELECT * FROM student WHERE birthday <=> NULL;
```



