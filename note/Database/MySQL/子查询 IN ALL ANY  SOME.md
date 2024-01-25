

```javascript
-- 子查询
/*
分类：
按出现位置
	SELECT后面
		仅支持标量子查询
	FROM后面
		支持表子查询
	WHERE或HAVING后面
		标量子查询
		列子查询
		行子查询
	EXISTS后面（相关子查询）
		表子查询
按结果集的行列数不同
	标量子查询：一行一列
	列子查询：一列多行
	行子查询：一行多列
	表子查询：多行多列
*/
```



WHERE后面

标量子查询

```javascript
-- 标量子查询
-- 比张三早出生的人
SELECT studentname
FROM studentinfo
WHERE birthday<(
	SELECT birthday
	FROM studentinfo
	WHERE studentname='张三'
);
-- 比张三早出生，学号比李建军大的人
SELECT studentname
FROM studentinfo
WHERE
 birthday<(
	SELECT birthday
	FROM studentinfo
	WHERE studentname='张三'
)
AND
studentno>(
	SELECT studentno
	FROM studentinfo
	WHERE studentname='李建军'
);

-- 最低工资大于50号部门最低工资的部门id及其最低工资
SELECT department_id,MIN(salary)
FROM employees
GROUP BY department_id
HAVING MIN(salary)>(
	SELECT MIN(salary)
	FROM employees
	WHERE department_id = 50
);
```



列子查询，使用IN ALL ANY



```javascript
-- 列子查询

-- 返回location_id是1400或1700的部门中所有员工的姓名
SELECT last_name
FROM employees
WHERE department_id IN (
	SELECT department_id
	FROM locations
	WHERE location_id IN (1400,1700)
);

-- 返回其他部门中比job_id为'IT_PROG'部门任意工资低的员工的员工号，姓名，job_id以及salary
SELECT employee_id,job_id,salary
FROM employees
WHERE salary < ANY(
	SELECT salary
	FROM employees
	WHERE job_id = 'IT_PROG'
)AND job_id <> 'IT_PROG';
-- 或
SELECT employee_id,job_id,salary
FROM employees
WHERE salary < (
	SELECT MAX(salary)
	FROM employees
	WHERE job_id = 'IT_PROG'
)AND job_id <> 'IT_PROG';
```



行子查询

```javascript
-- 行子查询
SELECT * 
FROM employees
WHERE (employee_id,salary) = (
	SELECT MIN(employee_id),MAX(salary)
	FROM employees
);
-- 等同于
SELECT *
FROM employees
WHERE employee_id = (
	SELECT MIN(employee_id)
	FROM employees
)
AND salary = (
	SELECT MAX(salary)
	FROM employees
);
```



SELECT后面

```javascript
-- SELECT后子查询
-- 每个部门的员工数
SELECT d.*,(
	SELECT COUNT(*)
	FROM employees e
	WHERE e.department_id = d.department_id
) FROM departments d;
-- 效果同下
SELECT d.*,COUNT(e.department_id)
FROM departments d LEFT JOIN employees e
ON e.department_id = d.department_id
GROUP BY d.department_id;


-- 第二种
-- 员工号=102员工的部门名
SELECT (
          SELECT `department_name`
          FROM `departments` d
          INNER JOIN `employees` e
          ON d.`department_id`=e.`department_id`
          WHERE  e.`employee_id`=102
) 部门名;
```



FROM后面

```javascript
-- 查询每个部门平均工资的工资等级
-- 将子查询结果集与grade_level连接，必须取别名
SELECT da.*,j.grade_level
FROM (
	SELECT department_id,AVG(salary) AS avgsalary
	FROM employees
	GROUP BY department_id
) AS da JOIN job_grades AS j ON da.avgsalary BETWEEN j.lowest_sal AND j.highest_sal;
```



EXISTS后面

```javascript
-- 查询有员工的部门名
-- 连接
SELECT d.department_name
FROM employees AS e INNER JOIN departments AS d ON e.department_id=d.department_id
GROUP BY d.department_id;
-- 子查询
-- where in
SELECT department_name
FROM departments AS d
WHERE d.department_id IN (
	SELECT department_id
	FROM employees
);
-- exists
SELECT department_name
FROM departments AS d
WHERE EXISTS(
	SELECT *
	FROM employees AS e
	WHERE e.department_id=d.department_id
);
```

