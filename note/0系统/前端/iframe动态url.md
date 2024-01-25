```javascript
function grand(data) {
	rawJsonObject = grid.getStore().getById(data.id).json;
	var b = {
		"id": rawJsonObject.id,
		"roleCode": rawJsonObject.roleCode,
		"roleName": rawJsonObject.roleName,
		"memo": rawJsonObject.memo.trim()			//需要去掉多余的空格，否则报错
	}
	var url = contextPath + "/chapterAccessCtl.ctrl?method=toListView&role=" ;
	var a = encodeURI(Ext.encode(b));
	url += a;
	portTreeWindow = new sofa.Window({   
		title:"权限管理("+b.roleName+")",
		id:'thisWindow', 
		width:'95%',
		height:550,
		layout : 'fit',
		modal:true,     
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src=' + url + '></iframe>',
		closeAction:"close"});    
	if(portTreeWindow.tools[1])	
		portTreeWindow.tools[1].hidden = true;  //隐藏关闭按钮BUG #124779 权限管理界面最大化显示不全
	portTreeWindow.on("hide", onPortTreeWindowBye);
    portTreeWindow.show();
}
```

RoleAccessCtlController