Mybatis in 条件传参三种实现方法（直接$，List，[]）

https://blog.csdn.net/wh445306/article/details/111056331





第一种方法：in 条件为拼接好的字符串



如果直接传入拼接好的where in 条件， 比如（'111','222','333')，则需要使用${idlist}传参，即绝对引用，而不能使用#

, 如果使用#传参会被mybatis当成字符串再添加一层''引号，导致错误.

优点：简单方便，高效，缺点：不能防止SQL注入





第二种方法：in 条件为List对象  

in条件直接传入List对象，让mybatis再去拼接生成in条件，这个很麻烦,但是可以防止SQL注入





第三种方法：in 条件为String[] 数组



in条件直接传入[]数组对象，让mybatis再去拼接生成in条件，这个很麻烦,但是可以防止SQL注入



如果项目大，其实可以同时重载三种都实现，我一般都会这样，实现三种DAO接口，service层相同方法名，根据不同的模块不同的需求调用不同的实现层



Service:



```javascript
int deleteMenuByIdList(String idlist,int delcount,int lastsort);
int deleteMenuByIdList(List<String> idlist, int delcount,int lastsort);
int deleteMenuByIdList(String[] idlist, int delcount,int lastsort);
```

Dao:

```javascript
//用这种写法方便，idlist直接拼接好，xml中用 in ${idlist}接受参数
int deleteMenuByIdList(@Param("idlist")String idlist, @Param("delcount")int delcount, @Param("lastsort")int lastsort);
//用这种写法直接传List对象，xml中再写循环拼接，麻烦
int deleteMenuByIdList2(@Param("idlist")List<String> idlist, @Param("delcount")int delcount, @Param("lastsort")int lastsort);
//用这种写法直接传String[]数组，xml中再写循环拼接，麻烦
int deleteMenuByIdList3(@Param("idlist")String[] idlist, @Param("delcount")int delcount, @Param("lastsort")int lastsort);
```

（2，3）的xml文件中不需要做修改，只需要修改一下id对应到DAO的方法名即可。

mappper.xml



1，

```javascript
    <delete id="deleteMenuByIdList" >
      delete from s_menu  where menu_id in ${idlist};
      update s_menu set sort=sort-#{delcount} where sort >= #{lastsort} and menu_id not in ${idlist};
    </delete>
```

 

2，

```javascript
    <delete id="deleteMenuByIdList2" >
        delete from s_menu where menu_id in
        <foreach collection="idlist" item="menu_id" separator="," open="(" close=")">
            #{menu_id}
        </foreach>
      ;update s_menu set sort=sort-#{delcount} where sort >= #{lastsort} and menu_id not in
       <foreach collection="idlist" item="menu_id" separator="," open="(" close=")">
           #{menu_id}
       </foreach>;
    </delete>
```

3，

```javascript
    <delete id="deleteMenuByIdList3" >
        delete from s_menu where menu_id in
        <foreach collection="idlist" item="menu_id" separator="," open="(" close=")">
            #{menu_id}
        </foreach>
        ;update s_menu set sort=sort-#{delcount} where sort >= #{lastsort} and menu_id not in
        <foreach collection="idlist" item="menu_id" separator="," open="(" close=")">
            #{menu_id}
        </foreach>;
    </delete>
```

 