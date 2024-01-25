1.进入home目录

```javascript
ssh-keygen
```

回车使用默认值即可



2.生成一个pub文件，里面是key

复制后到github，settings，sshkey里面设置



3.新设置一个远程别名

```javascript
git remote add origin_ssh github复制的ssh地址
```



4.向这个别名push

首次需要输入yes确认