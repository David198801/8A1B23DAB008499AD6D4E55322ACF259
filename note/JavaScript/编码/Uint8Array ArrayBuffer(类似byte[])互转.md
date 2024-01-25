https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Uint8Array



1. 浏览器端

Numer 转化为ArrayBuffer

```javascript
// int8, uint8 --> bytes
let i8a = 0x56, u8a = 0xfc
let bytes_i8a = new Int8Array([i8a]).buffer
let bytes_u8a = new Uint8Array([u8a]).buffer
// int16, uint16 --> bytes
let i16a = 0x55aa, u16a = 0xF5F0
let bytes_i16a = new Int16Array([i16a]).buffer
let bytes_u16a = new Uint16Array([u16a]).buffer
// int32, uint32 --> bytes
let i32a = 0x55aa55aa, u32a = 0xF5F0F1F2
let bytes_i32a = new Int32Array([i32a]).buffer
let bytes_u32a = new Uint32Array([u32a]).buffer
// int64, uint64 --> bytes
let i64a = 0x1122334455667788, u64a = 0xF1F2F3F4F5F6F7F0
let bytes_i64a = new Int64Array([i64a]).buffer
let bytes_u64a = new Uint64Array([u64a]).buffer
```



```javascript
// float, double --> bytes
let f32 = 0.1234, f64 = 0.9876543
let bytes_f32 = new Float32Array([f32]).buffer
let bytes_f64 = new Float64Array([f64]).buffer
```



ArrayBuffer转化为Number

```javascript
// let buffer = new Uint8Array([...]).buffer
// X 表示Number的具体类型， 
// 如 Int8, Uint8, Int16, Uint16, Int32, Uint32, Int64, Uint64, Float32, Float64
// let number = new Xarray(buffer)[0] 
// 比如:

let buffer = new Uint8Array([65, 66, 67, 68]).buffer
let number = new Uint32Array(buffer)[0]
// 1145258561
```



2. 后端Node

Number 转 ArrayBuffer

```javascript
// let buffer = new Buffer(size) /// size为number类型所占字节数
// buffer.writeXLE(number) /// 小端顺序写入， X为Number类型
// buffer.writeXBE(number) /// 小端顺序写入， X为Number类型
// 比如：

let number = 3.1415926 // PI
let buffer = Buffer.alloc(8) // double 类型占8字节
buffer.writeDoubleLE(number) // 小端顺序写入number
```



ArrayBuffer转 Number

```javascript
// let buffer = new Buffer([...])
// let number = buffer.readXLE(0) // 小端顺序读取
// let number = buffer.readXBE(0) // 大端顺序读取
// 如:
let buffer = Buffer.from([0, 0, 0, 1])
let number = buffer.readUInt32LE(0)
// 1677216
```

