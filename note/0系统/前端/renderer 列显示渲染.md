

```javascript
//费用类型
function typeRenderer(value,meta,record){
}
```

value，实际的值

meta，

record，行记录的对象







```javascript
// 渲染相关人员
    function usersRenderer(value,meta,record){
        var returnTxt = '';
        returnTxt += '<%=i18n.getString(commandPagePath, "creator")%>';
        returnTxt += record.get('handleName');//BUG #198733 [北京][广发银行][托管系统5.0_清算][高]20180411001(excel导入电子指令在支付指令管理页面的相关人员列无法显示经办人员名称)
        returnTxt += "<br/>";
        returnTxt += '<%=i18n.getString(commandPagePath, "otherCheckors")%>';
        //花旗银行 复核为空时显示undefined
        if(record.get('otherCheckerName')==null){
       	 	returnTxt +="";
        }else{
        	returnTxt += record.get('otherCheckerName');
        }
        returnTxt += "<br/>";
        returnTxt += '<%=i18n.getString(commandPagePath, "checkor")%>';
        returnTxt += record.get('checkerName');
        returnTxt += "<br/>";
      	//30683需求规格说明书_中信证券_支付指令界面增加“支付人员” zhangliang 20160515
        returnTxt += '支付：';
        if(record.get("executorId")==null){
        	 returnTxt +="";
        }else{
       		 returnTxt += record.get('executorName');
        }
        
     	// STORY #45318 广发银行PDF指令管理需求几点优化 wxy 2017-8-8
        if(!Ext.isEmpty(record.get('controllerName'))){
        	returnTxt += '<br/>监控员：' + record.get('controllerName');
        }
        if(!Ext.isEmpty(record.get('accountantName'))){
        	returnTxt += '<br/>核算员：' + record.get('accountantName');
        }
        return returnTxt;
    }
```

