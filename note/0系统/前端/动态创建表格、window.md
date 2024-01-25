https://blog.csdn.net/qq_21419015/article/details/80838974

修改window

```javascript
formWindow.removeAll()
formWindow.add(grid)
formWindow.doLayout()
```



创建window

```javascript
var temp2 = new sofa.Window({  
        title:'我的第一个组件',  
        width:400,   //Number型，也可以是字符串类型 width:'90%'
        height:300,  
        layout:'fit',  
        renderTo:Ext.getBody()   //新创建的组件 渲染到什么位置
    })
```



```javascript
var myGrid = new Ext.grid.GridPanel({
	autoHeight: true,
	width: 382,
	//renderTo: 'myGrid',
	loadMask: true,//加载缓冲图标
	//forceFit:true,//自动列宽，可以不用在设置clumn的宽度
	store: new Ext.data.ArrayStore({
		data: [
			['1', 'AAA','descn1'], 
			['2', 'BBB','descn2'], 
			['3', 'CCC','descn3'], 
			['4', 'DDD','descn4']
		],
		fields: [
			{ name: 'id' }, 
			{ name: 'name' }, 
			{ name: 'descn' }]
	}),
	columns: [
	   { header: '编号', dataIndex: 'id'},
	   { header: '名称', dataIndex: 'name'},
	   { header: '描述', dataIndex: 'descn'},              
	]
});




var myWindow = new sofa.Window({  
	title:'我的第一个组件',  
	width:400,   //Number型，也可以是字符串类型 width:'90%'
	height:300,  
	layout:'fit',  
	renderTo:Ext.getBody()   //新创建的组件 渲染到什么位置
});
myWindow.add(myGrid);
myWindow.doLayout();
myWindow.show();
```

