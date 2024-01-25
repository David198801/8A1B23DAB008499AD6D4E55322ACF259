信息披露权限重复数据

```javascript
SELECT fcode, freport_code, fchapter_code, COUNT(*) as duplicate_count
FROM T_XB_ROLE_ACCESS_CTL
GROUP BY fcode, freport_code, fchapter_code
HAVING COUNT(*) > 1;
```



```javascript
DELETE FROM T_XB_ROLE_ACCESS_CTL
WHERE fid NOT IN (
SELECT max(fid)
FROM T_XB_ROLE_ACCESS_CTL
GROUP BY fcode || freport_code || fchapter_code
);
```



T_XB_ROLE_USER,用户表