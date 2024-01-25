<%@ page language="java" pageEncoding="utf-8" import="com.yss.sofa.framework.web.util.WebUtil"%>
<%@ page import="com.yss.sofa.framework.i18n.I18N"%>
<%@ taglib uri="http://sofa.ysstech.com/tag/html" prefix="html"%>
<%@ taglib uri="http://sofa.ysstech.com/tag/widget" prefix="c"%>
<html:jsp doctype="default">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${i18n.add}</title>
<html:resource />
<%@ taglib uri="http://sofa.ysstech.com/tag/html" prefix="html"%>
<%@ taglib uri="http://sofa.ysstech.com/tag/widget" prefix="c"%>
<style type="text/css">
.type{ color: #33CCFF;
       padding-left:10px;
       box-sizing:border-box; 
}
</style>

<script type="text/javascript" src="resources/script/reportConfig.js"></script>
<script type="text/javascript">



</script>
</head>
<html:onReady>
<!-- initListTreeForm() -->
</html:onReady>

<body>
	<c:ACL id="myACL" code="acs-xbrl@reportConfig" />
	<c:FormPanel id="myForm" onBeforeSubmit="formSubmit" url="./reportConfig.ctrl?method=saveReport">
		<c:Form.Button type="submit" text="提 交"/>
		<c:Form.Button type="reset" text="重 置" onClick="reset" />
        <c:container layout="column" style="margin:15px 0px 0px 15px;">
           <c:DefaultConfig labelWidth="108"/>
           <c:container columnWidth="1">
	            <c:text mode="hidden" id="id" /> 
	            <c:text mode="hidden" id="productName" />
				<!-- 取消新产品树 -->
				<%-- <c:Container columnWidth="0.5" layout="table">
						<c:Container width="70" style="margin-left: 40px;">
							<c:innerHtml>
								<font size="3" color="red">*</font>
								<label for="lfactShow1">基金代码：</label>
							</c:innerHtml>
						</c:Container>
						<c:Container  id="productID_add" width = "130">
						</c:Container>
			</c:Container> --%>
				
					<c:TreeListView id="reportTemplate_day" width="200" displayField="reportName" valueField="reportType" 
							label="${i18n.reportType}" multiSelect="false" labelWidth="70" validate="required" 
							labelAlign="right" queryParam="name" remote="true" minChars="1" onSelect="role_access" 
							url="./reportConfig.ctrl?method=queryTaskCodeByQuery&operateType=add&root=root"/>
					<c:listview id="reportTemplate_temp_day" width="200" label="${i18n.tempReportType }"
	 				    labelWidth="70" visible="false" validate="none"  onSelect="show_temp_option"
	 					labelAlign="right" valueField="FREPORT_TYPE" displayField="FREPORT_NAME" onBlur="checkIsChangeIncomePayDate" 
	 					url="./reportConfig.ctrl?method=queryTempReportType" multiSelect="false"
	 					params="{functionCode:\"acs-xbrl@reportConfig\"}"/>	
	 			<c:container >
					<c:TreeListView id="productTreeForm" label="${i18n.productCode}" multiSelect="true"
						valueField="nodeId" textParam="text"   queryParam="keyword" lazyInit="true" 
						onlySelectLeaf="isSelected" minChars="<%=product_minChars%>" remote="true"
						width="200" url="<%=paramPath +\"productTree.ctrl?method=productLevel\"%>"
						params="{functionCode:\"acs-xbrl@reportConfig\",byACL:\"true\",operationCode:\"query\"}">
					</c:TreeListView>
				</c:container>
 					<c:listview id="reportTemplate_zmsms" width="200" label="招书类型"
	 				    labelWidth="70" visible="false" validate="none"  
	 					labelAlign="right" valueField="FREPORT_TYPE" displayField="FREPORT_NAME"  
	 					url="./reportConfig.ctrl?method=queryTempReportType" multiSelect="false"
	 					params="{functionCode:\"acs-xbrl@reportConfig\"}"/>	
	 									
					<c:calendar id="itemDate_add" width="200" label="${i18n.incomeAllocationBaseDay }" editable="true" validate="none" value="new Date()" visible="false"/>
					<c:calendar id="incomePayDate_add" width="200" label="${i18n.incomePayLastTime }" editable="true" validate="none" value="new Date()" visible="false"/>
					
			        <c:text id="reportNo" label="${i18n.reportTradeNo }" width="200" visible="false" validate="none"  emptyText="请输入数字"/>
		            <c:calendar id="reportDate_add" width="200" label="${i18n.reportDate}" editable="true" validate="required" value="new Date()"  />
				    <c:calendar id="submitDate_add" width="200" label="${i18n.sendDate}" editable="true"   onChange="changeReportDate" />
			    <c:innerHtml id="noteDate">
		    		<div id="note" style="display:block;margin-top:0px;margin-left:35px;margin-right:10px; ">
						<font size="1" color="red">送出日期设置之后按此日期报送，否则根据参数设置自动生成送出日期！</font>
					</div>
				</c:innerHtml>
		    </c:container>
		</c:container>

	</c:FormPanel>
</body>
</html:jsp>
