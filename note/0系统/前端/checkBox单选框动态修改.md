

```javascript
Ext.override(Ext.form.Checkbox, {
	setBoxLabel: function(boxLabel){
		this.boxLabel = boxLabel;
		if(this.rendered){
			this.wrap.child('.x-form-cb-label').update(boxLabel);
		}
	}
});
```

