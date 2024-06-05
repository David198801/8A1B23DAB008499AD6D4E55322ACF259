数据读取按字段删除 报错SQL执行出错

2024-05-15 09:28:27.393 ERROR --> [T_DI_LCJYSJ]数据入库出错!
[SOFA-061226P]：SQL执行出错[INSERT INTO T_DI_LCJYSJ (ftzzh,fzh_code,fsec_code,fsec_name,fsec_type,fcurrency_name,ftrade_mode,finvest_type,ftrade_id,ftrade_channel,fjzzbjd,fcjrq,fjsrq,freal_jsrq,fxjjs_id,fvolume,famount,fqs_amount,fdata_source,fplatform,fcounterparty,fbz,FIMP_DATE,FIMP_FILE,FREAD_FLAG,FIMP_FILE_NAME,FDELETED ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0) ]！
处理方法：
	请检查相关数据库连接是否正常或ＳＱＬ是否有兼容问题
java.sql.SQLException: 索引中丢失  IN 或 OUT 参数:: 1

**数据读取配置管理里面用varchar，不要用varchar2**