

```javascript
function getUserName(userId) {
    return new Promise((resolve) => {
		$.ajax({
			url:"http://127.0.0.1:9090/test/user?action=getUserName",
			type: "GET",
			data:{userId:userId},
			dataType: "json",
			success:function(data, textStatus){
				resolve(data.userName);
			}
		});
    });
}

async function printUserName() {
	console.log("start")
    let userName = await getUserName("00001");
    console.log(userName);
}
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













https://www.cnblogs.com/lvdabao/p/es6-promise-1.html

https://www.mk2048.com/blog/blog_hhcc20ibcb.html



https://segmentfault.com/a/1190000007535316

```javascript
var p1 = new Promise((resolve, reject) => { 
	setTimeout(function(){
		resolve(1)
	},2000)
})
var p2 = new Promise((resolve, reject) => { 
	setTimeout(function(){
		resolve(2)
	},1000)
 })



p1.then(l=>{console.log(l);return p2}).then(l=>console.log(l))
```



Promise写法

```javascript
function takeLongTime() {
    return new Promise(resolve => {
        setTimeout(() => resolve("long_time_value"), 1000);
    });
}

takeLongTime().then(v => {
    console.log("got", v);
});
```

async/await写法

```javascript
function takeLongTime() {
    return new Promise(resolve => {
        setTimeout(() => resolve("long_time_value"), 1000);
    });
}

async function test() {
    const v = await takeLongTime();
    console.log("got",v);
}

test();
```





