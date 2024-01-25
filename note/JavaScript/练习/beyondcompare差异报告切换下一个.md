

```javascript
var l = document.querySelectorAll("td.TextItemSigAdd,td.TextItemSigDel,td.TextItemSigDiffMod,td.TextItemSigLeftMod,td.TextItemSigRightMo")
var count = -1
var oldStyle = false
var oldEle = false
document.onkeydown = function(event){

	if(event.keyCode==37){
		if(count>0){
			count--
		}
	}else{
		count++
	}
	
	if(count>l.length-1){
		count=0
	}

	var i = l[count]
	//console.log(count)
	
	if(oldEle && oldStyle){
		oldEle.style=oldStyle
	}
	
	oldStyle = i.style
	i.style = "border:2px red solid"
	i.scrollIntoView()
	
	oldEle = i
	
	
}
```

