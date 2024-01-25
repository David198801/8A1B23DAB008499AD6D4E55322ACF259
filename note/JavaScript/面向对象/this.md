this代表函数所属的环境

通过对象调用函数，obj.func()，则this为obj

直接调用函数，this默认为window

函数中调用函数，this默认为window



构造函数中的this，严格模式下为undefined，非严格模式下为window