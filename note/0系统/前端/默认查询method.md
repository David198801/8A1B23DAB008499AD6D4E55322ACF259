grid，onRender="gridLoad"，onRender事件触发js函数



简单的可以用onRender="search"



base_rate.js

```javascript
function gridLoad(){
	var paramArr =[];
	//删除标记条件，在未删除数据中查找
	paramArr.push(setParamProperty("deleted", "false", "=", "Boolean"));
	
	//启用日期查询条件，精确查找
	var endtime = searchTime.getValue();
	if(endtime != "" ){
		paramArr.push(setParamProperty("endTime", endtime, ">=","Date"));
		paramArr.push(setParamProperty("startTime", endtime, "<=","Date"));
	}
	paramArr.push(setParamProperty("checked","asc","order",""));
	//排序条件，按照createTime排序
	paramArr.push(setParamProperty("createTime", "desc", "order"));
	
	Ext.getCmp('grid').setUrl("baseRate.ctrl?method=search");
	var params = Ext.encode(paramArr);
	Ext.getCmp('grid').setRequestParams({params:params});
	Ext.getCmp('grid').reload();
}
```

