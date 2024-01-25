

```javascript
SELECT 
                  CC.FCONTEXTPATH,
                  CC.FSYMBOLICNAME,
                  CC.FVERSION,
               F.FID AS FUNCID,
               F.FNAME AS FUNCNAME,
               F.FCODE AS FUNCCODE,
               F.FBASEURI AS FUNCBASEURI,
               F.FHOMEURI AS FUNCHOMEURI,
               F.FPARENTID AS FUNCPARENTID,
               F.FDESC AS FUNCDESC,
               F.FCUSTOMERCODE AS FCUSTOMERCODE,
	           F.FCUSTOMERNAME AS FCUSTOMERNAME,
               F.FDYNAMICFUNCTIONPROVIDER as FDYNAMICFUNCTIONPROVIDER,
               F.ISPORTLET AS ISPORTLET,
               O.FID AS OPERID,
               O.FNAME AS OPERNAME,
               O.FCODE AS OPERCODE,
               O.FURI AS OPERURI,
               O.FACTIONGROUP AS OPERACTIONGROUP,
               O.FUNCTIONID AS OPERFUNCID,
               O.FACTIONS AS OPERACTIONS,
               O.FREQUESTMETHOD AS OPERREQUMETHOD,
                 O.FBIZMETHOD AS OPERBIZMETHOD,
                 FCHILD.FID AS FCHILD_FUNCID,
                 FCHILD.FNAME AS FCHILD_FUNCNAME,
                 FCHILD.FCODE AS FCHILD_FUNCCODE,
                 FCHILD.FBASEURI AS FCHILD_FUNCBASEURI,
                 FCHILD.FHOMEURI AS FCHILD_FUNCHOMEURI,
                 FCHILD.FPARENTID AS FCHILD_FUNCPARENTID,
                 FCHILD.FDESC AS FCHILD_FUNCDESC,
                 FCHILD.ISPORTLET AS FCHILD_ISPORTLET,
                 OCHILD.FID AS OCHILD_OPERID,
                 OCHILD.FNAME AS OCHILD_OPERNAME,
                 OCHILD.FCODE AS OCHILD_OPERCODE,
                 OCHILD.FURI AS OCHILD_OPERURI,
                 OCHILD.FACTIONGROUP AS OCHILD_OPERACTIONGROUP,
                 OCHILD.FUNCTIONID AS OCHILD_OPERFUNCID,
                 OCHILD.FACTIONS AS OCHILD_OPERACTIONS,
                 OCHILD.FREQUESTMETHOD AS OCHILD_OPERREQUMETHOD,
                 OCHILD.FBIZMETHOD AS OCHILD_OPERBIZMETHOD
            FROM T_DEPLOY_FUNCTION F 
            LEFT JOIN T_DEPLOY_OPERATION O 
                 ON F.FID = O.FUNCTIONID
            LEFT JOIN (SELECT FF.FID,FF.FCODE,FF.FNAME,FF.FBASEURI,
                      FF.FHOMEURI,FF.FDESC,FF.FPARENTID,
                      FF.FCOMPONENTID,
                      FF.ISPORTLET FROM T_DEPLOY_FUNCTION FF 
                   WHERE FPARENTID IS NOT NULL AND FPARENTID <>1 AND FPARENTID<>-1) FCHILD
                 ON F.FID = FCHILD.FPARENTID
            LEFT JOIN T_DEPLOY_OPERATION OCHILD 
                 ON FCHILD.FID = OCHILD.FUNCTIONID
            INNER JOIN (
                  SELECT MAX(FID) AS FID,C.FSYMBOLICNAME,C.FCONTEXTPATH,C.FVERSION FROM T_DEPLOY_COMPONENT C 
                         GROUP BY C.FSYMBOLICNAME,C.FCONTEXTPATH,C.FVERSION
            )CC ON F.FCOMPONENTID = CC.FID
            ORDER BY CC.FSYMBOLICNAME,CC.FVERSION ASC
```



```javascript
SELECT FCODE,FHOMEURI FROM ACS.T_DEPLOY_FUNCTION 
WHERE FCODE IN (SELECT FCODE FROM ACS.T_DEPLOY_FUNCTION GROUP BY FCODE HAVING COUNT(FCODE)>1)
AND FHOMEURI LIKE '%jsp'
ORDER BY FCODE ;
```



```javascript
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-app/pages/appFuncMgr.jsp' WHERE fcode = 'sofa-app-appfunc';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-app/pages/application.jsp' WHERE fcode = 'sofa-app-application';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/aclquery.jsp' WHERE fcode = 'sofa-authorization-aclquery';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/dataacl_list.jsp' WHERE fcode = 'sofa-authorization-dataacl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/handover_list.jsp' WHERE fcode = 'sofa-authorization-handover';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/orgacl_list.jsp' WHERE fcode = 'sofa-authorization-orgacl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/roleacl_list.jsp' WHERE fcode = 'sofa-authorization-roleacl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/roleexclude_list.jsp' WHERE fcode = 'sofa-authorization-roleexclude';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-authorization/pages/useracl_list.jsp' WHERE fcode = 'sofa-authorization-useracl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-log/pages/config/logConfig.jsp' WHERE fcode = 'sofa-log-config';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-log/pages/log/log.jsp' WHERE fcode = 'sofa-log-manage';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/orgMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-org';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/userMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-user';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/positionMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-post';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/districtMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-region';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/dictCategoryMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-category';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/dataDictMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-dictionary';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/specialDateMgr.ctrl?method=listPageData' WHERE fcode = 'sofa-foundation-specialcalendar';
```



```javascript
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/orgMgr.ctrl?' WHERE fcode = 'sofa-foundation-org';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/userMgr.ctrl?' WHERE fcode = 'sofa-foundation-user';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/positionMgr.ctrl?' WHERE fcode = 'sofa-foundation-post';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/districtMgr.ctrl?' WHERE fcode = 'sofa-foundation-region';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/dictCategoryMgr.ctrl?' WHERE fcode = 'sofa-foundation-category';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/dataDictMgr.ctrl?' WHERE fcode = 'sofa-foundation-dictionary';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/sofa-basalinfo/specialDateMgr.ctrl?' WHERE fcode = 'sofa-foundation-specialcalendar';
```



```javascript
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/appFuncMgr.jsp' WHERE fcode = 'sofa-app-appfunc';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/application.jsp' WHERE fcode = 'sofa-app-application';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/aclquery.jsp' WHERE fcode = 'sofa-authorization-aclquery';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/dataacl_list.jsp' WHERE fcode = 'sofa-authorization-dataacl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/handover_list.jsp' WHERE fcode = 'sofa-authorization-handover';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/orgacl_list.jsp' WHERE fcode = 'sofa-authorization-orgacl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/roleacl_list.jsp' WHERE fcode = 'sofa-authorization-roleacl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/roleexclude_list.jsp' WHERE fcode = 'sofa-authorization-roleexclude';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/useracl_list.jsp' WHERE fcode = 'sofa-authorization-useracl';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/config/logConfig.jsp' WHERE fcode = 'sofa-log-config';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/pages/log/log.jsp' WHERE fcode = 'sofa-log-manage';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/orgMgr.ctrl?' WHERE fcode = 'sofa-foundation-org';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/userMgr.ctrl?' WHERE fcode = 'sofa-foundation-user';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/positionMgr.ctrl?' WHERE fcode = 'sofa-foundation-post';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/districtMgr.ctrl?' WHERE fcode = 'sofa-foundation-region';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/dictCategoryMgr.ctrl?' WHERE fcode = 'sofa-foundation-category';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/dataDictMgr.ctrl?' WHERE fcode = 'sofa-foundation-dictionary';
UPDATE ACS.T_DEPLOY_FUNCTION SET FHOMEURI ='/specialDateMgr.ctrl?' WHERE fcode = 'sofa-foundation-specialcalendar';
```

