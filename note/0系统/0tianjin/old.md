

[DealValuationCall.rar](assets/DealValuationCall.rar)



[FjzcValuationBO.rar](assets/FjzcValuationBO.rar)





检查请求



valuation_list.jsp

```javascript
var messageHideDefer = -1;

	//更新估值处理完成的进度
	var updateValProgress = function (progress,resultInfo) {
	    if (progress >= 1) {
	    	 var str = checkVoucher(resultInfo);
		        if(str != "") {
		        	sofa.alert({
				            title : '提示信息',
				            msg : "估值处理完成<br/>" + str,
				            width: 400,
				            maxHeight : 200
			        });
		        } else { 
	       	 		Ext.MessageBox.updateProgress(1, "100%",resultInfo);
					messageHideDefer = Ext.defer(function () { Ext.MessageBox.hide(); }, 2000);
		        }
	        Ext.getCmp("mytoolbar").setDisabled(false);
	        searchSubmit("2");

	        return;
	    }
	    Ext.MessageBox.updateProgress(progress, Math.round(progress * 100) + "%");
	    Ext.defer(function () {
	    	Ext.Ajax.request({
	    		timeout : 1000 * 60 * 60,
	    		method: "POST",
	    		async: false,
	    		url :"voucherDealResult.ctrl?method=checkval",
	    		success: function(xhr) {
	    			var executing = xhr.responseText;
	    			progress = parseFloat(executing);
	    			var info = "";
	    			if(progress-1 >= 0){
	    				info="估值处理完成";
	    			}
	    			updateValProgress(progress,info);

	    			showZfcheckResult();

					//检查日增长率最高限
					// var productId = searchProduct.getValue();
					// Ext.Ajax.request({
					// 	method: "POST",
					// 	async: false,
					// 	url: "./valuation.ctrl?method=checkValDayRateOfRise",
					// 	params: {"productId" :productId,
					// 		"date" : Ext.util.Format.date(queryDate.getValue(),'Y-m-d')},
					// 	success: function(xhr) {
					// 		if(xhr.responseText!= null && xhr.responseText!= ''){
					// 			clearTimeout(messageHideDefer);
					// 			sofa.alert(xhr.responseText);
					// 		}
					// 	},
					// 	failure: function(m,s){
					// 		sofa.error('', m.responseText);
					// 	}
					// });
	    		},
	    		failure: function(m,s){
	    			Ext.getCmp("mytoolbar").setDisabled(false);
	    			sofa.error('', m.responseText);
	    		}
	    	});
	    }, 2000);
	}
```

ValuationController.java

```javascript
	@RequestMapping(params = "method=checkValDayRateOfRise")
	public void checkValDayRateOfRise(HttpServletRequest request, HttpServletResponse response) throws BizException {
		String productId = request.getParameter("productId");
		String date = request.getParameter("date");
		HandleParam param = new HandleParam();
		param.setProductIds(productId);
		param.setBusDate(DateUtil.stringtoDate(date, "yyyy-MM-dd"));

		try {
			AJAXUtil.success(response, valuationBo.checkValDayRateOfRise(param));
		} catch (Exception e) {
			AJAXUtil.failure(response, "");
		}
	}
```

ValuationBO

```javascript
	public String checkValDayRateOfRise(HandleParam param){


		ParameterProgramService parameterProgramService = ServiceFactory.getInstance().getService(ParameterProgramService.class);
		//String JZRZZLZGX = parameterProgramService.getPublicPramaByCode("JZRZZLZGX", "");
		String JZRZZLZGX = parameterProgramService.getValueByCode(param.getProductIds(),"JZRZZLZGX", "");
		if(JZRZZLZGX!=null && JZRZZLZGX.length()>0){
			BigDecimal limit = DBUtil.getBigDecimal(JZRZZLZGX);//净值日增长率上限

			Dao dao = this.getDao(getDataSourceMapping());
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT FCOST,FYCOST FROM T_FA_VALUATION WHERE FSUBJECT_CODE=? AND FPRODUCT_ID=? AND FDATE=?");
			List<Object[]> list = dao.findBySql(sql.toString(), new Object[]{"930", param.getProductIds(), param.getBusDate()});
			if(ListUtil.isNotEmpty(list)){
				BigDecimal cost = DBUtil.getBigDecimal(list.get(0)[0]);
				BigDecimal ycost = DBUtil.getBigDecimal(list.get(0)[1]);

				if(cost.compareTo(limit) > 0 || ycost.compareTo(limit) > 0){
					param.setDelSpecificValNorm(true);
					delete(param);
					return "净值日增长率超过设定的上限，未生成估值表";
				}

			}

		}
		return "";

	}
```

