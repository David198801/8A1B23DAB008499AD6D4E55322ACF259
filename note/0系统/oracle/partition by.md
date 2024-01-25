https://www.cnblogs.com/shengs/p/5954818.html



```sql
select empno, ename, job, hiredate, sal, deptno  
from (select empno, ename, job, hiredate, sal, deptno, rank() over(partition by deptno order by sal desc) r from emp)  
where r = 1;  
```

