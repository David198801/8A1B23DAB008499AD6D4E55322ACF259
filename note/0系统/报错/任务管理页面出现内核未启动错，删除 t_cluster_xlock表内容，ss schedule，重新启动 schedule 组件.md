SOFAException调度引擎内核未启动

![](assets/任务管理页面出现内核未启动错，删除%20t_cluster_xlock表内容，ss%20schedule，重新启动%20schedule%20组件_image_0.png)



任务管理页面出现内核未启动错，删除 t_cluster_xlock表内容，ss schedule，重新启动 schedule 组件

select * from t_cluster_xlock;

delete from t_cluster_xlock;





```javascript
delete from t_deploy_server;

delete from t_deploy_sa_relation;

delete FROM T_CLUSTER_XLOCK;
```

