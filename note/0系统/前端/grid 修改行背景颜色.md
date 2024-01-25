通过backgroundRender控制css的class为grid-background-checked等



修改操作列

放在view里

```javascript
<c:operation baseUrl="./paymentCommand.ctrl?" width="60"
		<c:param id="view" onClick="toDetail" ACL="toDetail" renderer = "handleBackCommandRender"/>
	</c:operation>
```



```javascript
function backgroundRender(r, param, meta){
		var checked = r.get('checked');
		var otherCheckStates = null;
		if(!Ext.isEmpty(r.get('otherCheckStates'))){
			otherCheckStates = parseInt(r.get('otherCheckStates'));
		}
		var handled = r.get('handleStatus');
		if(isZZDShow() && r.get('deleted')){
			meta.css = 'grid-background-deleted';//查看时,已删除的 add by gdy 2018/07/09
		}else if(isZZDShow() &&r.get('stopStatus')){
			meta.css = 'grid-background-stopStatus';//查看时,已暂停的 add  by gdy 2018/07/09
		}else if((handled) && (otherCheckStates == null || otherCheckStates == 0)){
			meta.css =  'grid-background-handled';
		}else if (!checked && (otherCheckStates == 1 || otherCheckStates == 2)){
			meta.css =  'grid-background-rechecked';
		} else if (checked){
			meta.css =  'grid-background-checked';
		}
	}
```



修改整行

jsp

```javascript
c:grid id="grid"  onBeforerender="renderRow">
```

js

```javascript
function renderRow(r, param, meta) {
	Ext.getCmp("grid").getView().getRowClass = function(r, rowIndex) {
		var fentryno = r.json.FENTRYNO;
			if ( (fentryno != "" || fentryno != null) && fentryno == "wpp" ) {
				return 'grid-background-gold';
			}
		}
}
```

