-- 使用LIMIT
SELECT id FROM city LIMIT 5 OFFSET 2; -- id：3、4、5、6、7
SELECT id FROM city LIMIT 2,5;

-- 排序
-- 默认升序 ASC
SELECT * FROM student ORDER BY id;
SELECT * FROM student ORDER BY id ASC; -- 同上
-- 降序 DESC
SELECT * FROM student ORDER BY id DESC;


-- 子查询

-- 比张三早出生的人
SELECT studentname
FROM studentinfo
WHERE birthday<(
	SELECT birthday
	FROM studentinfo
	WHERE studentname='张三'
);

-- 返回location_id是1400或1700的部门中所有员工的姓名
SELECT last_name
FROM employees
WHERE department_id IN (
	SELECT department_id
	FROM locations
	WHERE location_id IN (1400,1700)
);


-- 简单使用
SELECT SUM(i) FROM num;
SELECT MAX(i) FROM num;
SELECT MIN(i) FROM num;
SELECT AVG(i) FROM num;
-- COUNT(),返回行数
SELECT COUNT(i) FROM num;


-- 传入字符串，根据字符串长度
SELECT MAX(email) FROM student; -- 返回最长的字符串
SELECT MIN(loginpwd) FROM student; -- 返回最短的字符串，有空则返回空

-- 传入日期，根据日期的新老
SELECT MAX(borndate) FROM student; -- 返回最新的日期
SELECT MIN(borndate) FROM student; -- 返回最老的日期

-- 和DISTINCT搭配
SELECT COUNT(gradeid) FROM student; -- 14
SELECT COUNT(DISTINCT gradeid) FROM student; -- 5


-- 分组查询

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