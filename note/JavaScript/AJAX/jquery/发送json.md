通过$.ajax()向后段服务发送json数据必须设置contentType值为application/json

```javascript
function doAjax2(){
		
	var data = {};
		
	data["param1"] = "val1";
	data["param2"] = "val2";
		
	$.ajax({
		url:'${pageContext.request.contextPath }/rest/jsonBody',
		type:'POST',
		contentType:'application/json;charset=UTF-8',
		data:JSON.stringify(data),
	});
}
```

