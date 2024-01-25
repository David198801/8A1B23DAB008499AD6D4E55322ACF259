

```javascript
 Ext.Ajax.request({
	 method : 'POST',
	 url : "./iosubject.ctrl?method=moneyIOSubject",
	 params : null,
	 async : false,
	 success : function(xhr) {
		var data = Ext.decode(xhr.responseText);;
		if(data.total!="0"){
			iosubject.getStore().reload();
			Ext.getCmp("iosubjectWindow").show();
		}
		 return false;
	 },
	 failure : function(m, s, e) {
		 return false;
	 }
 });
```



```javascript
Ext.Ajax.request({
method:'POST',
url:"./accountBalanceCheckResult.ctrl?method=nearClearingDay",
async:false,
success: function(data) {
	transferDate_search.setValue(data.responseText);
},
failure : function(xhr){
	sofa.error('', xhr.responseText);
}
});
```

请求体发送json

```javascript
Ext.Ajax.request({
method:'POST',
url:"./accountBalanceCheckResult.ctrl?method=nearClearingDay",
async:false,
jsonData:Ext.encode({a:1,b:2}),
success: function(data) {
	
},
failure : function(xhr){
	
}
});
```

