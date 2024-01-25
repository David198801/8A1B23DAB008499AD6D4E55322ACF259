

```javascript
/**
 * 获取划款类型，放到划款类型设置窗口的多选框中，默认选中已保存类型
 */
function getTypesCheckbox(){
	Ext.Ajax.request({
		method: "GET",
		url: "feePaymentQH.ctrl?method=getTypes",
		success: function(xhr) {
			var json = Ext.decode(xhr.responseText);
			var typeList = json.typeList;
			var typeListBNPP = json.typeListBNPP
			if(typeList.length != 0){
				for(var i=0;i<typeList.length;i++){
					typesCheckbox.add(
						new Ext.form.Checkbox({
					        boxLabel:typeList[i].name,
					        inputValue:typeList[i].code,//typesCheckbox.items.items[0].inputValue
					        checked:checkType(typeList[i],typeListBNPP)
				    }));
				}
				typesCheckbox.doLayout();
			}
		},
		failure: function(){
			sofa.error('','获取划款类型失败');
		}
	});
}
/**
 * 默认选中已保存类型
 * @param type 传入一个类型
 * @param typeListBNPP 已保存的类型列表
 * @returns {Boolean}
 */
function checkType(type,typeListBNPP){
	for(var i=0;i<typeListBNPP.length;i++){
		if(type.code==typeListBNPP[i].code){
			return true;
		}
	}
	return false;
}
```



```javascript
/**
	 * 获取场外数据处理的划款类型
	 * @param response
	 * @param request
	 */
	@RequestMapping(params = "method=getTypes",method = RequestMethod.GET)
	public void getTypes(HttpServletResponse response, HttpServletRequest request){
		
		
		Map<String,List<Map<String,String>>> reList = new HashMap<String,List<Map<String,String>>>();
		
		List<Map<String,String>> typeList = new ArrayList<Map<String,String>>();
		//获取场外数据处理的划款类型
		List<Object[]> list = commonBO.findBySql("select DISTINCT  fname, fcode from T_PA_BUS_TYPE");
//		List<Object[]> list = commonBO.findBySql("select DISTINCT  fname, fcode from T_PA_BUS_TYPE WHERE rownum<=10");
		if(list != null){
			for (Object[] obj : list) {
				Map<String,String> map = new HashMap<String, String>();
				typeList.add(map);
				map.put("name", DBUtil.getString(obj[0]));
				map.put("code", DBUtil.getString(obj[1]));
			}
			reList.put("typeList", typeList);
		}
		
		List<Map<String,String>> typeListBNPP = new ArrayList<Map<String,String>>();
		//获取 划款类型设置 保存的划款类型，用于默认勾选已保存的划款类型
		list = commonBO.findBySql("select fcode from T_CD_FEEPAYMENTQH_TYPE");
		if(list != null){
			for (Object[] obj : list) {
				Map<String,String> map = new HashMap<String, String>();
				typeListBNPP.add(map);
				map.put("code", DBUtil.getString(obj[0]));
			}
			reList.put("typeListBNPP", typeListBNPP);
		}
		
		AJAXUtil.success(response,JSONUtil.toJson(reList));
	}
```

