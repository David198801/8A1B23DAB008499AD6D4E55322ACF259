

```javascript
-- 查询不同性别的人数
SELECT sex,COUNT(*) FROM studentinfo GROUP BY sex;
-- 查询不同性别的年级人数
SELECT sex,SUM(studentinfo.gradeid) FROM studentinfo GROUP BY sex;
-- 查询不同性别的最早生日
SELECT sex,MIN(studentinfo.birthday) FROM studentinfo GROUP BY sex;
-- 查询不同性别没有填写email的人数
SELECT sex,COUNT(studentinfo.studentno) FROM studentinfo WHERE email IS NULL GROUP BY sex;

-- HAVING 在GROUP BY的基础上再按条件筛选
-- 查询不同性别的人数中大于5人的
SELECT sex,COUNT(*) FROM studentinfo GROUP BY sex having COUNT(*)>5;

-- 查询领导编号>102的每个领导手下的最低工资>5000的领导编号是哪个，以及其手下最低工资
SELECT MIN(salary),manager_id
FROM emplyees
WHERE manager_id > 102 -- 与聚合函数无关的用WHERE
GROUP BY manager_id
HAVING MIN(salary) > 5000; -- 与聚合函数有关的用HAVING


-- 用函数结果分组
-- 按姓名长度分组,人数>1的姓名长度及人数
select count(*) as c,char_length(studentname)
from studentinfo
group by char_length(studentname)
having c>1;


-- 按多个字段分组
-- 按不同性别，不同姓名长度分组
select count(*),sex,CHAR_LENGTH(studentname)
from studentinfo
group by sex,char_length(studentname);


-- 添加排序
-- 按人数排序
SELECT COUNT(*) as c,sex,CHAR_LENGTH(studentname)
FROM studentinfo
GROUP BY sex,CHAR_LENGTH(studentname)
ORDER BY c DESC;
```

