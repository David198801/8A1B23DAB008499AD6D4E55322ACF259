

```javascript
Dao dao = this.getDao(Constant.ACS_COMMAND_MAIN_DATASOURCE_MAPPING);

        Condition condition = ConditionBuilder.create(Command.class).add(
                ExpressionHelper.eq("deleted",false)).add(
                ExpressionHelper.eq("transferDate",clearDate)).add(
                ExpressionHelper.eq("orgin",CashOriginConstant.PLZLDR)).add(
                ExpressionHelper.in("sourceDataCode",serialNos)).build();

        List<Command> list = (List<Command>)dao.findByCondition(condition);
```

