创建database

```javascript
create database testdb;
```

创建用户

```javascript
CREATE USER lzb WITH PASSWORD 'lzb';
```



创建schema

```javascript
create schema lzb;
```

授予该用户对新 schema 的权限

```javascript
GRANT ALL ON SCHEMA lzb TO lzb ;
```

直接创建和当前用户同名schema

注意：用户名与 schema 同名，且用户具有访问改 schema 的权限，用户连入数据库时，默认即为当前 schema。

```javascript
create schema AUTHORIZATION CURRENT_USER;
```

