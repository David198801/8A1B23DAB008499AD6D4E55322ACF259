

```javascript
<c:container labelWidth="120" >
					<c:listview id="fundAct" displayField="fundAct" minChars="2" validate="required" 
								valueField="fundAct"  pageSize = "15"  maxResultSize = "50" width="200"
								queryParam="keyword"  label="${i18n.fundAct }"
								template = "{tpName}-{fundAct}" fields="id,tpName,fundAct,productId"
								onlySelectLeaf="true" remote="true"  multiSelect="true"
								url="./bankSign.ctrl?method=search" 
								params='{params:\'[{"field":"deleted","value":"false","operation":"=","fieldType":"Boolean"},{"field":"rechecked","value":"true","operation":"=","fieldType":"Boolean"}]\'}'/>
```

