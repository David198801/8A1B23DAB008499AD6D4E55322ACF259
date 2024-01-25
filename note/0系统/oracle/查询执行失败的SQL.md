使用触发器记录错误的sql

```javascript
CREATE TABLE "T_LOGERR" 
   (	"FMSG" VARCHAR2(2000), 
    	"FTIME" DATE
   ) ;
```



```javascript
create or replace TRIGGER log_err after servererror on schema
DECLARE
  v_stack VARCHAR2(2000) := substr(dbms_utility.format_error_stack,1,2000);
  v_back VARCHAR2(2000);-- := substr(dbms_utility.format_error_backtrace,1,2000);
  v_num NUMBER;
  v_sql_text ora_name_list_t;
 v_msg VARCHAR2(4000);
  procedure track(p_text in varchar2) is
  begin
     insert into t_logerr(fmsg,ftime) values (p_text,SYSDATE);
  end;
begin
  v_stack := translate(v_stack,'''','"');
  track(v_stack);
  v_back := translate(v_back,'''','"');
  if v_back is not null then track(v_back); end if;
  v_num  := ora_sql_txt(v_sql_text);
  v_msg := '';
  BEGIN
    FOR i IN 1..v_num LOOP
	    v_msg := v_msg||v_sql_text(i);
    END LOOP;
   track(v_msg);
  EXCEPTION
    WHEN VALUE_ERROR THEN NULL;
  END;
end;
```

分行记录

```javascript
create or replace TRIGGER log_err after servererror on schema
DECLARE
  v_stack VARCHAR2(2000) := substr(dbms_utility.format_error_stack,1,2000);
  v_back VARCHAR2(2000);-- := substr(dbms_utility.format_error_backtrace,1,2000);
  v_num NUMBER;
  v_sql_text ora_name_list_t;
  procedure track(p_text in varchar2) is
  begin
     insert into t_logerr(fmsg,ftime) values (p_text,SYSDATE);
  end;
begin
  v_stack := translate(v_stack,'''','"');
  track(v_stack);
  v_back := translate(v_back,'''','"');
  if v_back is not null then track(v_back); end if;
  v_num  := ora_sql_txt(v_sql_text);
  BEGIN
    FOR i IN 1..v_num LOOP
      track(to_char(i,'0000')||':'||v_sql_text(i));
    END LOOP;
  EXCEPTION
    WHEN VALUE_ERROR THEN NULL;
  END;
end;
```



https://dba.stackexchange.com/questions/40318/is-it-possible-in-oracle-to-trace-sql-statements-that-result-in-errors

https://stackoverflow.com/questions/492705/is-there-any-way-to-log-all-failed-sql-statements-in-oracle-10g



https://www.google.com/search?q=oracle+get+failed+sql&newwindow=1&client=aff-cs-360se&hs=0iY&biw=1280&bih=552&sxsrf=ALiCzsbjBzxZ4Jn_DFreiOXyzU3gHLp_Lg%3A1652843614738&ei=XmSEYo_RLI-XkPIPrt6luAk&ved=0ahUKEwiP-Jmkiuj3AhWPC0QIHS5vCZcQ4dUDCA4&uact=5&oq=oracle+get+failed+sql&gs_lcp=Cgdnd3Mtd2l6EAM6BwgjELADECc6BwgAEEcQsAM6BQgAEIAEOgQIABATOgYIABAeEBM6CAgAEAgQHhATOgoIABAIEAoQHhATOgwIABAIEA0QChAeEBM6BAgAEA06BggAEA0QHjoICAAQCBANEB46BAgAEB46BQghEKABSgQIQRgASgQIRhgAULcFWOlaYNxeaARwAXgBgAGzBogB4UOSAQozLTE1LjIuMy4xmAEAoAEByAEKwAEB&sclient=gws-wiz