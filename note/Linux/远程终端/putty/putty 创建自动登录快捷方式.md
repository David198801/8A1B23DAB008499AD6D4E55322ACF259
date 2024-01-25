1. 保存登录地址。双击打开putty.exe，在session页面填写 Host Name 和 Port，并在 Saved Sessions 填写具有标识性的名称，如 host100，点击 Save 保存。



2. putty.exe 右键 创建快捷方式，重命名为具有标识性的名称，如 putty.exe - host100。



3. 在创建的快捷方式上，右键属性，在 目标 一栏追加以下内容，注意与原内容空格隔开。

```javascript
-load "host100" -ssh -l 用户名 -pw 登入密码
```

如

```javascript
D:\P\putty64.exe -load "129" -ssh -l root -pw root
```

