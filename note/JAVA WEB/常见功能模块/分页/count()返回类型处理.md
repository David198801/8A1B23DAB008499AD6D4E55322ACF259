可以使用Number类型的intValue方法，为abstract方法，被子类实现

```javascript
public int queryForBooksCount() {
    String sql = "select count(*) from `t_book`";
    Number count = queryForSingleValue(sql);
    return count.intValue();
}
```

