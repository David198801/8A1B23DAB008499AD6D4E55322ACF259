

```javascript
sofa.confirm("是否继续1？", function(btn){
	if(btn=='yes'){
	    sofa.confirm("是否继续2？", function(btn2){
			if(btn2=='yes'){
				console.log(111)
				Ext.MessageBox.hide();
			   return false;
			}
		});
	   return false;
	}
});


```



```javascript
sofa.confirm("是否继续1？", function(btn){
	if(btn=='yes'){
		doSomething1();
	   return false;
	}
});

function doSomething1(){
	console.log("do something1")
	sofa.confirm("是否继续2？", function(btn){
		if(btn=='yes'){
			doSomething2();
		   return false;
		}
	});
}

function doSomething2(){
	console.log("do something2")
	sofa.confirm("是否继续3？", function(btn){
		if(btn=='yes'){
			doSomething3();
			Ext.MessageBox.hide();
		   return false;
		}
	});
}

function doSomething3(){
	console.log("do something3")
}

```



```javascript
sofa.confirm("是否继续1？", function(btn){
	if(btn=='yes'){
		console.log("do something1")
	    sofa.confirm("是否继续2？", function(btn2){
			if(btn2=='yes'){
				console.log("do something2")
				sofa.confirm("是否继续3？", function(btn3){
					if(btn3=='yes'){
						console.log("do something3")
						Ext.MessageBox.hide();
					   return false;
					}
				});
			   return false;
			}
		});
	   return false;
	}
});
```



```javascript
Ext.MessageBox.confirm("confirm","是否继续1？", function(btn){
	if(btn=='yes'){
		doSomething1();
	}
});

function doSomething1(){
	console.log("do something1")
	Ext.MessageBox.confirm("confirm","是否继续2？", function(btn){
		if(btn=='yes'){
			doSomething2();
		}
	});
}

function doSomething2(){
	console.log("do something2")
	Ext.MessageBox.confirm("confirm","是否继续3？", function(btn){
		if(btn=='yes'){
			doSomething3();
		}
	});
}

function doSomething3(){
	console.log("do something3")
}
```



```javascript
function p1(){
	return new Promise((resolve, reject) => { 
		Ext.MessageBox.confirm("confirm","是否继续1？", function(btn){
			if(btn=='yes'){
				resolve()
			}
		});
	})
}

function p2(){
	return new Promise((resolve, reject) => { 
		Ext.MessageBox.confirm("confirm","是否继续2？", function(btn){
			if(btn=='yes'){
				resolve()
			}
		});
	})
}

function p3(){
	return new Promise((resolve, reject) => { 
		Ext.MessageBox.confirm("confirm","是否继续3？", function(btn){
			if(btn=='yes'){
				resolve()
			}
		});
	})
}

p1().then(res=>{
	console.log("do something1");
	return p2();
}).then(res=>{
	console.log("do something2");
	return p3();
}).then(res=>{
	console.log("do something3");
})
```

async函数中return的会被Promise.resolve()包装，await 接受resolve的参数

```javascript
function confirm(title) {
  return new Promise((resolve,reject) => Ext.MessageBox.confirm("confirm",title, btn => btn === 'yes' ? resolve() : reject()))
}

await confirm('是否继续1？')
console.log("do something1");
await confirm('是否继续2？')
console.log("do something2");
await confirm('是否继续3？')
console.log("do something3");
```



```javascript
async function confirm(title) {
  return {then:(resolve,reject) => Ext.MessageBox.confirm("confirm",title, btn => btn === 'yes' ? resolve() : reject())}
}

await confirm('是否继续1？')
console.log("do something1");
await confirm('是否继续2？')
console.log("do something2");
await confirm('是否继续3？')
console.log("do something3");
```

