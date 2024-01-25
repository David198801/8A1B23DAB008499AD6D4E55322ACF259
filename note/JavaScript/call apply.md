call可以传入this

apply和call类似，传入数组参数

```javascript
function func(a,b,c){
    //...this....
}

//func.call(this,a,b,c);
function func2(this,a,b,c){
    func.apply(this,a,b,c);
}

//func.apply(this,[a,b,c]);
function func2(){
    func.apply(this,arguments);
}
```

