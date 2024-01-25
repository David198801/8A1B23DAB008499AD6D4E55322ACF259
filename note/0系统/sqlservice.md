没dao就用

```javascript
ParametersSQLService sqlService = ServiceFactory.getInstance()
                .getService(ParametersSQLService.class);
            StringBuffer str = new StringBuffer("select ftype,fmessage from T_THIRD_BAFFLE where ftype=?");
            List<Object> params = new ArrayList<Object>();
            params.add("yz");
            String responseStr ="";
            List<Object[]> list = sqlService.findBySql(str.toString(), params.toArray());
            if(!list.isEmpty()){
                Object[] obj=list.get(0);
                responseStr = DBUtil.getString(obj[1]); 
            }
```

