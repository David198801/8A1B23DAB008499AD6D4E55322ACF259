package com.yss.acs.datainterface.controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yss.acs.api.fundacc.service.AccountWorkViewService;
import com.yss.acs.api.parameters.service.DictionaryService;
import com.yss.acs.api.parameters.vo.Dictionary;
import com.yss.acs.api.parameters.vo.SecurityAccount;
import com.yss.acs.common.constant.DataAuthConstant;
import com.yss.acs.common.constant.DataSourceConstant;
import com.yss.acs.common.constant.DictionaryConstant;
import com.yss.acs.common.util.ACLUtil;
import com.yss.acs.common.util.DBUtil;
import com.yss.acs.common.util.DataLookMapperUtil;
import com.yss.acs.common.util.DateTools;
import com.yss.acs.common.util.ListUtil;
import com.yss.acs.common.util.LogFormat;
import com.yss.acs.common.util.MapUtil;
import com.yss.acs.common.util.ServiceUtil;
import com.yss.acs.common.util.SetUtil;
import com.yss.acs.common.util.StringUtil;
import com.yss.acs.datainterface.api.service.ImportDataService;
import com.yss.acs.datainterface.api.vo.DataFile;
import com.yss.acs.datainterface.batchresult.biz.ImportDataResultBO;
import com.yss.acs.datainterface.biz.ImportConfigBO;
import com.yss.acs.datainterface.biz.ImportDataBO;
import com.yss.acs.datainterface.biz.ImportDataStatusBO;
import com.yss.acs.datainterface.biz.ProductInterfacePathBO;
import com.yss.acs.datainterface.biz.PublicInterfaceGroupBO;
import com.yss.acs.datainterface.constant.ExceptionCodeConstant;
import com.yss.acs.datainterface.exception.ACSDatainterfaceException;
import com.yss.acs.datainterface.vo.DataLook;
import com.yss.acs.datainterface.vo.DiPath;
import com.yss.acs.datainterface.vo.GridColumn;
import com.yss.acs.datainterface.vo.ImportData;
import com.yss.acs.datainterface.vo.ImportDataStatus;
import com.yss.acs.datainterface.vo.Page;
import com.yss.acs.datainterface.vo.StoreField;
import com.yss.sofa.dataimport.config.ImpConfig;
import com.yss.sofa.dataimport.config.ImpDbConfig;
import com.yss.sofa.dataimport.config.ImpGroupConfig;
import com.yss.sofa.dataimport.config.TableFieldConfig;
import com.yss.sofa.framework.biz.BizException;
import com.yss.sofa.framework.biz.BizObjectFactory;
import com.yss.sofa.framework.context.SOFAContext;
import com.yss.sofa.framework.dao.Dao;
import com.yss.sofa.framework.dao.hibernate.HibernateUtil;
import com.yss.sofa.framework.exception.SOFAException;
import com.yss.sofa.framework.exception.SOFARuntimeException;
import com.yss.sofa.framework.id.IDGenerator;
import com.yss.sofa.framework.id.NUIDGenerator;
import com.yss.sofa.framework.log.SOFALogger;
import com.yss.sofa.framework.log.SOFALoggerFactory;
import com.yss.sofa.framework.services.SOFAServiceException;
import com.yss.sofa.framework.services.ServiceFactory;
import com.yss.sofa.framework.services.dataimport.DataImpService;
import com.yss.sofa.framework.services.dataimport.FileLookupService;
import com.yss.sofa.framework.services.dataimport.ImpStatus;
import com.yss.sofa.framework.services.dataimport.RuleVariable;
import com.yss.sofa.framework.util.DateUtil;
import com.yss.sofa.framework.web.controller.SimpleCRUDController;
import com.yss.sofa.framework.web.util.AJAXUtil;
import com.yss.sofa.framework.web.util.ApplicationContextHolder;
import com.yss.sofa.framework.web.util.JSONUtil;

/**
 * 公共数据读取
 * 
 * @see
 * @author zhanglq
 * @version 1.000, 2011-9-16
 * @since 1.000, 2011-9-16
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/importData")
public class ImportDataController extends SimpleCRUDController {
	
	/**
	 * 用于判断异常信息
	 */
	private boolean forDebug;
	
	/**
	 * sofa日志
	 */
	private static SOFALogger log = SOFALoggerFactory.getSOFALogger(ImportDataController.class);
	/**
	 * 注入导入数据类BO
	 */
	@Autowired
	private ImportDataBO importDataBO;
    
    /**
     * 
     */
    @Autowired
    private ImportDataStatusBO importDataStatusBO;
	/**
	 * 注入接口群信息管理业务类BO
	 */
	@Autowired
	private ProductInterfacePathBO pipBO;
	/**
	 * 注入公共数据读取接口群管理BO
	 */
	@Autowired
	public PublicInterfaceGroupBO intGroupBO;
	/**
	 * 查找文件
	 * */
	private FileLookupService fileLookup = (FileLookupService) ServiceFactory.getInstance()
			.getService(FileLookupService.class);
	/**
	 * 注入数据
	 * */
	private DataImpService dataImp = (DataImpService) ServiceFactory.getInstance().getService(
			DataImpService.class);
	
	/**
	 * 数据读取配置管理业务类
	 */
	@Autowired
	private ImportConfigBO importConfigBO;
	
//	private TargetDataMagService targetData = (TargetDataMagService) ServiceFactory.getInstance()
//			.getService(TargetDataMagService.class);
	
	/**
	 * 公共接口群权限
	 */
	private final static String SIMPLE_TYPE_INTERFACE = "com.yss.acs.datainterface.auth.datatype.ImportDataTypeAuth_ImportDataTypeDetailProviderServiceImpl";
	/**
	 * 产品接口群权限
	 */
	private final static String SIMPLE_TYPE_INTERFACE_PRO = "com.yss.acs.datainterface.auth.datatype.ImportDataTypeAuth_ImportDataTypeProDetailProviderServiceImpl";

	
	/**
	 * 得到数据源信息
	 * @return String
	 */
	public String getDataSourceMapping() {

		return DataSourceConstant.ACS_DI_DATASOURCE_MAPPING;
	}

	/**
	 * controller注入
	 * 
	 * @param importDataStatusBO 文件读取状态BO
	 */
	@Autowired
	public ImportDataController(ImportDataStatusBO importDataStatusBO) {

		super("com.yss.acs.datainterface.vo.ImportDataStatus");

		// this.setFormView("importData_form");

		this.setListView("importDataPublic_list");

		this.setFunctionCode("acs-datainterface@importData");

		// this.setDetailView("importData");

		this.setGenericCRUDBO(importDataStatusBO);

	}

	/**
	 * 获取接口群树
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 已检查异常基类
	 */
	@RequestMapping(params = "method=getDIGroupTree")
	public void getDIGroupTree(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException {
		String isdetailStr = request.getParameter("isdetail");
		String keyword = request.getParameter("keyword");
		boolean isdetail = StringUtil.isEmpty(isdetailStr) ? false
				: Boolean.valueOf(isdetailStr.trim());
		List<Map<String, Object>> impGroupConfig = fileLookup.lookupGroups(null);
		String jsonPub = null;
		String jsonPro = null;
		String jsonText = null;
		if(ListUtil.isNotEmpty(impGroupConfig)){
			Collections.sort(impGroupConfig, new Comparator<Map<String, Object>>() {

				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return DBUtil.getString(o1.get("name")).compareTo(DBUtil.getString(o2.get("name")));
				}
			});
		}
		if (StringUtil.isEmpty(keyword)) {
			jsonPub = importDataBO.toTreeJson(impGroupConfig, isdetail,"1");
			jsonPro = importDataBO.toTreeJson(impGroupConfig, isdetail,"2");
			
			StringBuilder json = new StringBuilder();
			json.append("[");
			
			if(!"[]".equals(jsonPub)){
				json.append("{");
				json.append("id:\"公共接口群|公共接口群|1\",");
				json.append("\"text\":\"公共接口群\",");
				json.append("\"code\":\"公共接口群\",");
				json.append("\"interfaceGroupType\":\"1\",");
				json.append("\"children\":");
				json.append(jsonPub);
				json.append("},");
			}
			
			if(!"[]".equals(jsonPro)){
				json.append("{");
				json.append("id:\"产品接口群|产品接口群|2\",");
				json.append("\"text\":\"产品接口群\",");
				json.append("\"code\":\"产品接口群\",");
				json.append("\"interfaceGroupType\":\"2\",");
				json.append("\"children\":");
				json.append(jsonPro);
				json.append("},");
			}
			
			if(!"[".equals(json)){
				json.setLength(json.length() - 1);
			}
			json.append("]");
			
			jsonText = json.toString();
		} else {
			jsonText = importDataBO.findByKeyword(impGroupConfig, isdetail, keyword);
		}

		AJAXUtil.success(response, jsonText);

	}

	/**
	 * 获取接口群树
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 已检查异常基类
	 */
	@RequestMapping(params = "method=getDIGroupTreeNotMon")
	public void getDIGroupTreeNotMon(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException {
		String isdetailStr = request.getParameter("isdetail");
		String interfaceGroupType = request.getParameter("interfaceGroupType");
		boolean isdetail = StringUtil.isEmpty(isdetailStr) ? false
				: Boolean.valueOf(isdetailStr.trim());
		String isAcl = request.getParameter("isAcl");
		List<String> authCodeList = new ArrayList<String>();
		List<Map<String, Object>> impGroupConfig = new ArrayList<Map<String,Object>>();
		//搜索关键字
		String keywords = request.getParameter("keywords");
		//需要权限
		if(!"false".equals(isAcl)){
			//获取设置了权限的接口集合
			//1级节点
			Map<String, List<String>> acl = ACLUtil.findACLByService(DataAuthConstant.DATATYPE_DATAAUTH, null,null);
			
			
			DictionaryService dicService = ServiceFactory.getInstance().getService(DictionaryService.class);
			List<Dictionary> list = dicService.findByType("接口群类型");
			Set<String> oldTypeData = new HashSet<String>();
			if(ListUtil.isNotEmpty(list)){
				for(Dictionary dic : list){
					oldTypeData.add(dic.getCode());
				}
			}
			
			if(MapUtil.isNotEmpty(acl)){
				List<String> pubList = acl.get(SIMPLE_TYPE_INTERFACE);
				if(ListUtil.isNotEmpty(pubList)){
					for(String id : pubList){
						authCodeList.add("1"+id);
						if(oldTypeData.contains(id)){
							continue;
						}
						importDataBO.getDetailInterfaceByParent(id,authCodeList);
					}
				}
				List<String> proList = acl.get(SIMPLE_TYPE_INTERFACE_PRO);
				if(ListUtil.isNotEmpty(proList)){
					for(String id : proList){
						authCodeList.add("2"+id);
						if(oldTypeData.contains(id)){
							continue;
						}
						importDataBO.getDetailInterfaceByParent(id,authCodeList);
					}
				}
			}
			if(ListUtil.isNotEmpty(authCodeList)){
				// 按设置了的权限取
				impGroupConfig = fileLookup.lookupGroupNodes(authCodeList);
			}
		}else{
			// 取全部的
			impGroupConfig = fileLookup.lookupGroups(null);
		}
		
		Collections.sort(impGroupConfig, new Comparator<Map<String, Object>>() {

			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return DBUtil.getString(o1.get("name")).compareTo(DBUtil.getString(o2.get("name")));
			}
		});
		
		//现在的配置，最多三级
		List<Map<String, Object>> impGroupConfig_new = new ArrayList<Map<String,Object>>();
		if(StringUtil.isNotEmpty(keywords)){
			for(Map<String, Object> map : impGroupConfig){
				List<Map<String, Object>> groupList = (List<Map<String, Object>>) map.get("leafs");
				List<Map<String, Object>> groupListNew1 = new ArrayList<Map<String,Object>>();
				if(ListUtil.isNotEmpty(groupList)){
					for(Map<String, Object> map1 : groupList){
						List<Map<String, Object>> groupList2 = (List<Map<String, Object>>) map1.get("leafs");
						List<Map<String, Object>> groupListNew2 = new ArrayList<Map<String,Object>>();
						if(ListUtil.isNotEmpty(groupList2)){
							for(Map<String, Object> map2 : groupList2){
								//满足条件的直接过滤
								if(DBUtil.getString(map2.get("name")).contains(keywords)){
									groupListNew2.add(map2);
								}
							}
						} else {
							if(DBUtil.getString(map1.get("name")).contains(keywords)){
								groupListNew1.add(map1);
							}
						}
						if(ListUtil.isNotEmpty(groupListNew2)){
							map1.put("leafs", groupListNew2);
							groupListNew1.add(map1);
						}
					}
				}
				if(ListUtil.isNotEmpty(groupListNew1)){
					map.put("leafs", groupListNew1);
					impGroupConfig_new.add(map);
				}
			}
		}else{
			impGroupConfig_new.addAll(impGroupConfig);	
		}
		
		String jsonText = "";
		if(StringUtil.isEmpty(interfaceGroupType)){
			String jsonPub = importDataBO.toTreeJsonNotMon(impGroupConfig_new, isdetail,"1",false);
			String jsonPro = importDataBO.toTreeJsonNotMon(impGroupConfig_new, isdetail,"2",false);
			StringBuilder json = new StringBuilder();
			json.append("[");
			
			if(!"[]".equals(jsonPub) && StringUtil.isNotEmpty(jsonPub)){
				json.append("{");
				json.append("id:\"公共接口群|公共接口群|1\",");
				json.append("\"text\":\"公共接口群\",");
				json.append("\"code\":\"公共接口群\",");
				json.append("\"interfaceGroupType\":\"1\",");
				json.append("\"children\":");
				json.append(jsonPub);
				json.append("},");
			}
			
			if(!"[]".equals(jsonPro) && StringUtil.isNotEmpty(jsonPro)){
				json.append("{");
				json.append("id:\"产品接口群|产品接口群|2\",");
				json.append("\"text\":\"产品接口群\",");
				json.append("\"code\":\"产品接口群\",");
				json.append("\"interfaceGroupType\":\"2\",");
				json.append("\"children\":");
				json.append(jsonPro);
				json.append("},");
			}
			
			if(!"[".equals(json)){
				json.setLength(json.length() - 1);
			}
			json.append("]");
			
			jsonText = json.toString();
		}else{
			jsonText = importDataBO.toTreeJsonNotMon(impGroupConfig_new, isdetail,interfaceGroupType,false);
		}
		
		AJAXUtil.success(response, jsonText);
	}
	
	/**
	 * 获取接口群树只用于监控中心
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 已检查异常基类
	 */
	@RequestMapping(params = "method=getDIGroupTreeNotMonForMonitoringCenter")
	public void getDIGroupTreeNotMonForMonitoringCenter(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException {
		String isdetailStr = request.getParameter("isdetail");
		boolean isdetail = StringUtil.isEmpty(isdetailStr) ? false
				: Boolean.valueOf(isdetailStr.trim());
		String interfaceGroupType = request.getParameter("interfaceGroupType");
		//获取设置了权限的接口集合
		//1级节点
		Map<String, List<String>> acl = ACLUtil.findACLByService(DataAuthConstant.DATATYPE_DATAAUTH, null,null);
		List<String> authCodeList = new ArrayList<String>();
		if(MapUtil.isNotEmpty(acl)){
			List<String> pubList = acl.get(SIMPLE_TYPE_INTERFACE);
			if(ListUtil.isNotEmpty(pubList)){
				for(String id : pubList){
					authCodeList.add("1"+id);
					importDataBO.getDetailInterfaceByParent(id,authCodeList);
				}
			}
			List<String> proList = acl.get(SIMPLE_TYPE_INTERFACE_PRO);
			if(ListUtil.isNotEmpty(proList)){
				for(String id : proList){
					authCodeList.add("2"+id);
					importDataBO.getDetailInterfaceByParent(id,authCodeList);
				}
			}
		}
		List<Map<String, Object>> impGroupConfig = new ArrayList<Map<String,Object>>();
		if(ListUtil.isNotEmpty(authCodeList)){
			// 按设置了的权限取
			impGroupConfig = fileLookup.lookupGroupNodes(authCodeList);
		}
		String jsonText = importDataBO.toTreeJsonNotMon(impGroupConfig, isdetail,interfaceGroupType,true);
		jsonText = jsonText.replaceAll("text", "name");
		AJAXUtil.success(response, jsonText);
	}

	/**
	 * 获取文件列表
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 已检查异常基类
	 * @throws ParseException 异常信息
	 */
	@RequestMapping(params = "method=showDIList")
	public void showDIList(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException, ParseException {

		// 说明：读数中的产品树，如果直接勾选更节点，则会导致非叶子节点（明细资产）的ID也会被作为产品ID传递过来（json）
		// 这样会导致在getRule中调用findProductByID查询不到产品报空引用，因为findProductByID未对查询的结果集做空判断
		// 但如果直接选择产品节点，则没有问题，目前为了避免该空异常，在findProductByID中加了是否为空的判断
		String json = request.getParameter(this.COMMON_PARAMS_JSON_DATA);
		String keyword = request.getParameter("keyword");
		String filter = request.getParameter("filter");
		// 是否是读数完成后的结果请求
		String readbtn = request.getParameter("readbtn");
		ImportData importData = (ImportData) JSONUtil.toObject(json, ImportData.class);
		importData.setPathList(importData.getPathList().replace("\\", "/"));
		importData.setMatchType("all");
		List<DataFile> dataFileList = new ArrayList<DataFile>();
		List<DataFile> returnList = new ArrayList<DataFile>();
		List<DataFile> reList = new ArrayList<DataFile>();
		List<DataFile> errorList = new ArrayList<DataFile>();

		List<String> groups = new ArrayList<String>();
		List<String> configs = new ArrayList<String>();
		List<String> fileNameList = null;
		Map<String, DiPath> dimap = null;
		if ("product".equals(keyword)) {
			dimap = this.pipBO.getPathByProdouctIds(importData.getProductList().split(","),
					DateUtil.stringtoDate(importData.getClearDate(), DateUtil.LONG_DATE_FORMAT),
					false,importData.getDiGroup().split(","));
			if (importData.isClient()) {
				groups = Arrays.asList(dimap.keySet().toArray(new String[0]));
			}
		} else {
			dimap = this.pipBO.getPathByDiCode(importData.getDiGroup().split(","),
					DateUtil.stringtoDate(importData.getClearDate(), DateUtil.LONG_DATE_FORMAT));
			if (importData.isClient()) {
				groups = Arrays.asList(dimap.keySet().toArray(new String[0]));
			}
		}

		if (importData.getFiles() != null) {// 客户端文件列表
			fileNameList = Arrays.asList(importData.getFiles().split(","));
		}
		List<RuleVariable> ruleVariables = importDataBO.getRule(
				importData.getProductList(),
				DateTools.convertDateToString("yyyy-MM-dd",
						DateTools.getStringToDate(importData.getClearDate())),
				new HashMap<String, List<String>>());

		IDGenerator gen = new NUIDGenerator();
		Map<String, List<String>> fileList = null;
		// Map<String, String> nameMap = null;
		StringBuilder diCodes = new StringBuilder();
		try {
			if (importData.isClient()) {
				fileList = fileLookup
						.verifyClientFile(fileNameList, groups, configs, ruleVariables);
			} else {
				fileList = this.importDataBO.lookupFiles(dimap, ruleVariables, importData
						.getClearDate().replaceAll("-", "").substring(0, 8), true);
			}
			Set<String> keys = fileList.keySet();
			// nameMap = fileLookup.lookupConfigNames();
			ImportDataService importDataService = ServiceUtil.getService(ImportDataService.class);
			for (String key : keys) {
				for (String path : fileList.get(key)) {

					// 前台按照组名称分组
					String groupName = "默认分组";
					ImpGroupConfig impGroupConfig = importDataService.queryImpGroupConfig(key);
					if (!impGroupConfig.getParentCode().equalsIgnoreCase("-1")) {
						List<Map<String, Object>> impGroupConfig1 = null;
						try {
							impGroupConfig1 = fileLookup.lookupGroups(impGroupConfig
									.getParentCode());
						} catch (Exception e) {
							AJAXUtil.failure(response, ExceptionCodeConstant.DATAIMPORT_004U);
						}
						if (ListUtil.isNotEmpty(impGroupConfig1)) {
							for (Map<String, Object> m : impGroupConfig1) {
								if (null != m.get("name")) {
									groupName = DBUtil.getString(m.get("name"));
									break;
								}
							}
						}
					}

					DataFile file = new DataFile();
					diCodes.append(key).append(",");
					file.setDiCode(key);
					file.setDiName(groupName+"/"+impGroupConfig.getName());
					file.setFileName(path.lastIndexOf("/") > -1 ? path.substring(path
							.lastIndexOf("/") + 1) : path.substring(path.lastIndexOf("\\") + 1));
					String fileType = path.substring(path.lastIndexOf(".") + 1);
					file.setFileType(fileType.indexOf("/") > -1 || fileType.indexOf("\\") > -1 ? "txt"
							: fileType);
					file.setFilePath(path);
					file.setFullFilePath(path);
					file.setId(gen.nextId());
					file.setClearDate(DateTools.getStringToDate(importData.getClearDate()));
					file.setProductList(importData.getProductList());
					if ("product".equals(keyword) && !importData.isClient()) {
						String productId = null;
						Map<String, String> pathMap = dimap.get(key).getPathMap();
						in: for (String absPath : pathMap.keySet()) {
							if (path.indexOf(absPath) > -1) {
								productId = pathMap.get(absPath);
								file.setProductId(productId);
								file.setProductName(dimap.get(key).getProductMap().get(productId));
								file.setType(dimap.get(key).getTypeMap().get(absPath));
								break in;
							}
						}

					}
					file.setClientUpload(importData.isClient());
					dataFileList.add(file);
				}

			}
			if (ListUtil.isNotEmpty(dataFileList)) {
				ImportDataResultBO importDataResultBO = (ImportDataResultBO) ApplicationContextHolder
						.getInstance(this).getBean("importDataResultBO");
				/**
				 * 手工读数同步更改核算工作台读数状态 暂时不做开关控制对于没有使用工作台的情况，读数完成后便调用一次 20160818
				 */
				Set<String> readSet = new HashSet<String>();
				Date accDate = null;
//				Map<String, Map<String, Object>> maps = importDataResultBO.doBeforeCheck(importData.getClearDate(),diCodes.toString());
				diCodes.setLength(0);
				for (DataFile dataFile : dataFileList) {
				    	Map<String, Object> reMap = null;
					// 验证文件是否已经读取过，
					// 公共数据不存在多产品公用的，所以这里展示否已经读取，不使用产品判断
					reMap = importDataResultBO.doBeforeCheck(dataFile.getDiCode(),
							dataFile.getFilePath(), "", 
							DateUtil.dateToString(dataFile.getClearDate(), "yyyy-MM-dd HH:mm:ss"));
					if(reMap==null){
						if("all".equals(filter)||filter.equals(DictionaryConstant.RESULT_STATUS_01)){
							dataFile.setFileStatus("未读取");
							returnList.add(dataFile);
						}						
						continue;
					}
					if (reMap.isEmpty()) {
						if (filter.equals(DictionaryConstant.RESULT_STATUS_01)) {
							dataFile.setFileStatus("未读取");
							returnList.add(dataFile);
						}
					} else {
						dataFile.setCreatorId(DBUtil.getString(reMap.get("creatorId")));
						dataFile.setTime(DBUtil.getString(reMap.get("time")));
						dataFile.setFileCount(DBUtil.getString(reMap.get("fileCount")));				
						dataFile.setDbCount(DBUtil.getString(reMap.get("dbCount")));
						dataFile.setFinishTime(DBUtil.getDate(reMap.get("finishTime")));
						if ((Boolean) reMap.get(DictionaryConstant.RESULT_STATUS_02)) {
							if (StringUtil.isEmpty(filter) || "all".equals(filter)
									|| filter.equals(DictionaryConstant.RESULT_STATUS_02)) {
								dataFile.setFileStatus("正在读取");
								returnList.add(dataFile);
							}
						} else if ((Boolean) reMap.get(DictionaryConstant.RESULT_STATUS_03)) {
							if (StringUtil.isEmpty(filter) || "all".equals(filter)
									|| filter.equals(DictionaryConstant.RESULT_STATUS_03)) {
								dataFile.setFileStatus("已读取");
								returnList.add(dataFile);
								if (StringUtil.isNotEmpty(readbtn) && "1".equals(readbtn)) {
									readSet.add(dataFile.getDiCode());
									if (accDate == null) {
										accDate = dataFile.getClearDate();
									}
								}
							}
						} else if ((Boolean) reMap.get(DictionaryConstant.RESULT_STATUS_04)) {
							if (StringUtil.isEmpty(filter) || "all".equals(filter)
							// 出错的也属于未读
									|| filter.equals(DictionaryConstant.RESULT_STATUS_04)) {
								dataFile.setFileStatus("读取出错");
								errorList.add(dataFile);
							}
						} else if ((Boolean) reMap.get(DictionaryConstant.RESULT_STATUS_WCLCG)) {
							if (StringUtil.isEmpty(filter) || "all".equals(filter)
							// 出错的也属于未读
									|| filter.equals(DictionaryConstant.RESULT_STATUS_WCLCG)) {
								dataFile.setFileStatus("未处理成功");
								returnList.add(dataFile);
							}
						} else {
							if (StringUtil.isEmpty(filter) || "all".equals(filter)
									|| filter.equals(DictionaryConstant.RESULT_STATUS_01)) {
								dataFile.setFileStatus("未读取");
								returnList.add(dataFile);
							}
						}
					}
				}
				if (SetUtil.isNotEmpty(readSet)) {
					AccountWorkViewService workservice = ServiceFactory.getInstance().getService(
							AccountWorkViewService.class);
					Map<String, Set<String>> map = new HashMap<String, Set<String>>();
					map.put("0", readSet);
					workservice.synchReadStatus(map, accDate);
					readSet.clear();
					readSet = null;
				}
			}
			dataFileList = null;
			log.info(SOFAContext.getInstance().getUserName() + "返回公共数据列表：》》》》》》》》》》》》》》》》》大小"
					+ returnList.size());
			reList.addAll(errorList);
			reList.addAll(returnList);
			this.success(response, reList, reList.size());
		} catch (SOFAServiceException e) {
			AJAXUtil.failure(response, e.getLocalizedMessage());
		}
	}

	/**
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @return String
	 */
	@RequestMapping(params = "method=toImportData")
	public String toImportData(HttpServletRequest request, HttpServletResponse response) {

		Map map = getPageI18ns("importDataPublic_list");

		request.setAttribute("i18n", map);

		return "importDataPublic_list";
	}
	
	/**
	 * 流程专用form表单
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @return String
	 */
	@RequestMapping(params = "method=toImportDataFlow")
	public String toImportDataFlow(HttpServletRequest request, HttpServletResponse response) {

		return "importDataFlow";
	}

	/**
	 * 方法详细说明，包括用途、注意事项、举例说明等。
	 * 
	 * @param dfJson 文件列表
	 * @param response 响应对象
	 * @throws SOFAException 异常信息
	 * @throws IOException 异常信息
	 * @author ltp
	 * @date 2015-1-5
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=execImportingData")
	public void execImportingData(String dfJson, HttpServletResponse response)
			throws SOFAException, IOException {
		StringBuilder errorMsg = new StringBuilder();
		// 执行前先清除一下，不然压力测试的时候，读取执行走的慢，状态更新会检测到上一次已经100的数据cwt2016-11-23
		SOFAContext.getInstance().removeAttribute("importDataProgressBar");
		List<DataFile> dfList = (List<DataFile>) JSONUtil.toList(dfJson, DataFile.class);
		long start = new Date().getTime();
		try {
			SOFAContext.getInstance().setAttribute("importDataProgressBar", "0");
			importDataBO.execImportingData(dfList, dataImp, new ImpStatus(), errorMsg, false);
			long end = new Date().getTime();
			log.info(LogFormat.logFormat(null, SOFAContext.getInstance().getUserName(), new Date(
					start), new Date(end), "数据读取", "数据读取结束", true));
			log.info(SOFAContext.getInstance().getUserName() + "发起的数据读取总用时：" + (end - start) / 1000
					+ "秒！");
		} catch (Exception e) {
			long end = new Date().getTime();
			log.info(LogFormat.logFormat(null, SOFAContext.getInstance().getUserName(), new Date(
					start), new Date(end), "数据读取", "数据读取异常：" + e.getMessage(), true));
		}
	}
	/**
	 * 设置导入数据进度条
	 * 
	 * @param dfJson
	 * @param response
	 */
	@RequestMapping(params = "method=setImportDataProgressBar")
	public void setImportDataProgressBar(String dfJson, HttpServletResponse response) {
		SOFAContext.getInstance().setAttribute("importDataProgressBar", "0");
	}

	/**
	 * 方法详细说明，包括用途、注意事项、举例说明等。
	 * 
	 * @param ids id
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws SOFAException 异常
	 * @author ltp
	 * @date 2015-1-5
	 */
	@RequestMapping(params = "method=checkStatus")
	public void checkStatus(String ids, HttpServletRequest request, HttpServletResponse response)
			throws SOFAException {
		try {
			ImportDataStatusBO bo = (ImportDataStatusBO) BizObjectFactory.getBizObject(
					ImportDataStatusBO.BEAN_ID, importDataStatusBO );
			String[] idArr = ids.split(";");
			StringBuilder importResult = new StringBuilder("");
			List<ImportDataStatus> listRead = bo.checkStatus(ids, "5");
			List<ImportDataStatus> listTemp = bo.findByIDs(ids);
			List<ImportDataStatus> listSuccess = bo.checkStatus(ids, "9");
			List<ImportDataStatus> listFailure = bo.checkStatus(ids, "0");
			if (ListUtil.isNotEmpty(listTemp) && listTemp.size() == idArr.length && ListUtil.isEmpty(listRead)) {
				importResult.append("读取完成:\n<br/>");
				if (ListUtil.isNotEmpty(listTemp)) {
					importResult.append("本次共选择").append(idArr.length).append("个文件,\n<br/>");
					Date begintime = listTemp.get(0).getBeginTime();
					long totaltime = (System.currentTimeMillis() - begintime.getTime()) / 1000;
					importResult.append("导入成功").append(listSuccess.size()).append("个文件,导入失败")
							.append(listFailure.size()).append("个文件,").append("共耗时")
							.append(totaltime).append("秒\n<br/>");
					if (ListUtil.isNotEmpty(listSuccess)) {
						importResult.append("导入成功文件信息如下： \n<br/>");
						for (ImportDataStatus importDataStatus : listSuccess) {
							importResult.append(importDataStatus.getImpFile()).append(":")
									.append("导入").append(importDataStatus.getCompleteRecordNum())
									.append("条").append(",耗时")
									.append(Double.valueOf(importDataStatus.getTotalTime()) / 1000)
									.append("秒\n<br/>");
						}
					}
					if (ListUtil.isNotEmpty(listFailure)) {
						importResult.append("导入失败文件信息如下：\n<br/>");
						for (ImportDataStatus importDataStatus : listFailure) {
							importResult.append(importDataStatus.getImpFile()).append(":")
									.append(importDataStatus.getDesc()).append("\n<br/>");
						}
					}

				}
				AJAXUtil.success(response, importResult.toString());
			} else {
				importResult.append((listSuccess.size() + listFailure.size()) * 100 / idArr.length);
				response.setStatus(202);
				AJAXUtil.success(response, importResult.toString());
			}
		} catch (Exception e) {
			AJAXUtil.failure(response, e.getLocalizedMessage());
		}

	}

	/**
	 * 访问成功，向前台发送可分页的数据
	 * 
	 * @param response 响应对象
	 * @param vos 当前页显示的数据
	 * @param rowTotle 数据总行书
	 * @throws SOFARuntimeException SOFA运行时异常
	 */
	private void success(HttpServletResponse response, Collection<?> vos, int rowTotle)
			throws SOFARuntimeException {

		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
		    out = response.getWriter();
			Page page = new Page();
			page.setTotal(rowTotle);
			page.setData(vos);
			//clob处理
			Object[] objects = vos.toArray();
			for(int i = 0 ; i < objects.length ; i++){
				
				if(objects[i] instanceof Map){
					
					Map<String,Object> map = (Map<String, Object>) objects[i];
					for(String key : map.keySet()){
						
						if(map.get(key) instanceof Clob){
							
							map.put(key,"Clob");
						}
					}
				}
			}
			out.write(JSONUtil.toJson(page));
		} catch (IOException ex) {
			AJAXUtil.failure(response, ex);
			throw new ACSDatainterfaceException(ExceptionCodeConstant.DATAIMPORT_000U, ex);
		} catch (IllegalArgumentException e) {
			log.error("向前台发送可分页的数据失败！"+e.getMessage());
		} finally{
			if(out != null){
				out.close();
			}
		}
	}

	/**
	 * 用来统一显示异常信息<br>
	 * 分为调试模式，非调试模式
	 * 
	 * @param response 响应对象
	 * @param e 异常
	 */
	public void showError(HttpServletResponse response, FileNotFoundException e) {

		if (forDebug) {
			AJAXUtil.failure(response, e);
		} else {
			AJAXUtil.failure(response, e.getMessage());
		}
	}
	 /**
	  * 数据显示，显示首行字段
	  * 
	 * @param productId
	 * @param zqdm
	 * @param gddm
	 * @param sDate
	 * @param eDate
	 * @param impFile
	 * @param impconfigCode
	 * @param startRow
	 * @param rowCount
	 * @return Page
	 * @throws SQLException
	 * @throws BizException
	 */
	public com.yss.sofa.framework.web.util.Page<Object[]> queryTargetData( String productId,String zqdm ,String gddm,String sDate, String eDate, String impFile, String impconfigCode, int startRow, int rowCount) throws SQLException, BizException 
			 {
	 	  	  com.yss.sofa.framework.web.util.Page page = new com.yss.sofa.framework.web.util.Page();
	 	  	  ImportDataService importDataService = ServiceUtil.getService(ImportDataService.class);
		 	  ImpConfig impConfig = importDataService.queryImpConfig(impconfigCode.substring(1),impconfigCode.substring(0,1));
		 	  ImpDbConfig dbconfig = impConfig.getImpDbConfig();
		 	  List<String> fields = getDbConfigFields(dbconfig);
		      Dao dao = this.importDataBO.getDao(this.getDataSourceMapping());
		      List<SecurityAccount> securityAccountList = importDataBO.queryProSecuAccounts(productId);//获得产品的证券账户信息
//		      StringBuilder gddms = new StringBuilder();//拼接所选产品的所有证券账户
		      List<String> gdcodes = null;
		      // 存放查询参数
		      List<Object> paramList = new ArrayList<Object>();
		      if (ListUtil.isNotEmpty(securityAccountList)) {
		    	  gdcodes = new ArrayList<String>();
			      for (SecurityAccount securityAccount : securityAccountList) {
			    	  gdcodes.add(securityAccount.getCode());
//			    	  gddms.append("'"+securityAccount.getCode()+"',");
			      }
//			      gdcodes = gddms.substring(0, gddms.length()-1).toString();
		      }
		      String tableName = dbconfig.getTableName();	//获取表名
		      StringBuilder sql = new StringBuilder();
		      sql.append("SELECT ").append(StringUtil.joinString(fields)).append(" FROM ").append(tableName);
		      sql.append(" WHERE ").append(" FIMP_DATE between ? and ? ");
		      paramList.add(sDate);
		      paramList.add(eDate);
		      //添加查询证券代码条件
		      String Tzqdm= DataLookMapperUtil.DATA_MAPPER.get(tableName);
		      //添加查询股东代码代码条件
		      String Tzqzh=DataLookMapperUtil.DATA_MAPPER.get(tableName+"1");
		      if(StringUtil.isNotEmpty(Tzqdm)&&StringUtil.isNotEmpty(zqdm)) {
		    	  //sql.append(" AND ").append(Tzqdm).append(" = '").append(zqdm).append("'");
		    	  sql.append(" AND ").append(Tzqdm).append(" like ? ");
		    	  paramList.add("%"+zqdm+"%");
			  }
		      if(StringUtil.isNotEmpty(Tzqzh)&&StringUtil.isNotEmpty(gddm)) {
		    	  sql.append(" AND ").append(Tzqzh).append(" = ? ");
		    	  paramList.add(gddm);
			  }
		      //查询所选产品的所有证券账户信息
		      if(StringUtil.isNotEmpty(Tzqzh)&&StringUtil.isNotEmpty(productId)&& StringUtil.isEmpty(gddm) && ListUtil.isNotEmpty(gdcodes)) {
		    	  sql.append(" AND ").append(StringUtil.createINSql2(Tzqzh, gdcodes.toArray()));
		    	  paramList.addAll(gdcodes);
			  }
		      sql.append(" AND ").append("FIMP_FILE");
		      if (null == impFile){
		    	  sql.append(" LIKE ? ");
		    	  paramList.add(impconfigCode+"_%");
		      }else{
		    	  sql.append(" = ? ");
		    	  paramList.add(impconfigCode+"_"+impFile);
		      }
		      //String sqlCnt = new StringBuilder().append("SELECT COUNT(1) FROM ").append(tableName).append(" WHERE ").append(" FIMP_DATE between '").append(sDate).append("' and '").append(eDate).append("' ").toString();
		      String sqlCnt = new StringBuilder().append("SELECT COUNT(*) FROM (").append(sql).append(") t ").toString();
		      List<Object[]> cntList = dao.findBySql(sqlCnt, paramList.toArray());
		      if(ListUtil.isNotEmpty(cntList)){			//查出数据总条数赋值给page的total字段
		    	 page.setTotal(DBUtil.getInt(cntList.get(0)));
		      }
		      
		      //db2数据库
		      //该sql查出表名、字段名、注释
		      String comment = "";
		      if(dao.getCurrentDBType().isDB2()){
		    	  tableName = tableName.toUpperCase();
		    	  comment = "select tabname,colname,remarks from syscat.columns where tabname = '" + tableName+"'";
		      } else if (dao.getCurrentDBType().isMYSQL()) {
		    	  // MySQL
		    	  comment = "select table_name, column_name, column_comment comments from information_schema.columns where table_name = '" + tableName + "' and table_schema = (select database())";
		      } else {
		    	  comment = "SELECT table_name,column_name,comments FROM user_col_comments where table_name = upper('" + tableName + "')";
		      }
		      List<Object[]> comList = dao.findBySql(comment, new Object[]{});
		      //BUG #203754 add by wuhg config配置文件与表字段不匹配抛出异常 2018-5-25
		      if(ListUtil.isEmpty(comList)||ListUtil.isEmpty(fields)||comList.size()<fields.size()){
			  throw new BizException("配置文件与数据库表字段不匹配");
		      }
		      int len = comList.size();
		      Object[] column = new Object[len+1];
		      column[0]="ST";						//表头序号字段显示
		      Map<String, String> commentMap = new HashMap<String, String>();
		      if(ListUtil.isNotEmpty(comList)){
		    	  for(int i=0; i<comList.size(); i++){	//用来显示表头字段(第一行显示字段)，默认取表字段对应的注释，没有则取字段名
		    		  Object[] obj = comList.get(i);
		    		  commentMap.put(DBUtil.getString(obj[1]).toUpperCase(), DBUtil.getString(null == obj[2] ? obj[1] : obj[2]).replace("(", "（").replace(")", "）").replace(".", "。"));
		    	  }
		      }
		      for (int i=0; i<fields.size(); i++) {
		    	  //column[i+1] = commentMap.get(fields.get(i).toUpperCase());
		    	  column[i+1] = fields.get(i).toUpperCase();
		      }
		      List list = new ArrayList();
		      list.add(column);
		      String fysql = getFysql(sql.toString(), rowCount, startRow >= page.getTotal() ? page.getTotal() : startRow,dao);	
		      List<Object[]> fyList = dao.findBySql(fysql, paramList.toArray());
		      //如果证券代码为空
		      if(StringUtil.isEmpty(Tzqdm) && StringUtil.isNotEmpty(zqdm)){
		    	  fyList.clear();
		      }
		      if(StringUtil.isEmpty(Tzqzh) && StringUtil.isNotEmpty(gddm)){
		    	  fyList.clear();
		      }
		      if(ListUtil.isNotEmpty(fyList)){
		    	  for(int i=0; i<fyList.size(); i++){
		    		  Object[] obj = fyList.get(i);
		    		  list.add(obj);
		    	  }
		      }
		      list.add(commentMap);
		      page.setData(list);
		      return page;
	 }
 	//数据分页显示
	 private String getFysql(String sql, int pageSize, int startRow, Dao dao) {
        	StringBuilder fy_sql = new StringBuilder();
    	     if (pageSize == 0){
    	        return sql;
    	     }
    	      int starNum = startRow;
    	      fy_sql.append(" select b.* from ( ");
    	      if(dao.getCurrentDBType().isDB2()){
    	        fy_sql.append(" SELECT a.*, rownumber() over(ORDER BY fimp_date ASC) AS rn from ( ").append(sql).append(" ) a )b  ");
    	        fy_sql.append(" where b.rn between ").append(starNum).append(" and ").append(starNum + pageSize - 1).append(" --SQLTD");
    	      }else if(dao.getCurrentDBType().isOracle()){
        	       fy_sql.append(" SELECT rownum st,a.* from ( ").append(sql).append(" ) a )b  ");
        	       fy_sql.append(" where st between ").append(starNum).append(" and ").append(starNum + pageSize - 1); 
    	      }else if(dao.getCurrentDBType().isMYSQL()) {
    	    	  starNum = starNum == 0 ? starNum + 1 : starNum;
       	       	fy_sql.append(" SELECT a.* from ( ").append(sql).append(" ) a )b  ");
       	       fy_sql.append(" limit ").append(starNum-1).append(" , ").append(pageSize);
    	      }
    	      return fy_sql.toString();
	}
	/**
	 * 原始数据浏览
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 异常
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=dataLook")
	public void dataLook(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException, SQLException {

		String diCode = request.getParameter("diCode");
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String zqdm = request.getParameter("zqdm");
		String gddm = request.getParameter("gddm");
		String productId = request.getParameter("productId");
		List<GridColumn> gridColumnList = new ArrayList<GridColumn>();
		List<StoreField> storeFieldList = new ArrayList<StoreField>();
		int rowCount = Integer.parseInt(StringUtil.isEmpty(request
				.getParameter(this.COMMON_PARAMS_ROW_COUNT)) ? "30" : request
				.getParameter(this.COMMON_PARAMS_ROW_COUNT));
		int startRow = Integer.parseInt(StringUtil.isEmpty(request
				.getParameter(this.COMMON_PARAMS_START_ROW)) ? "0" : request
				.getParameter(this.COMMON_PARAMS_START_ROW));
		try {
			com.yss.sofa.framework.web.util.Page<Object[]> lists = null;
			ImportDataService importDataService = ServiceUtil.getService(ImportDataService.class);
			ImpGroupConfig impGroupConfig = importDataService.queryImpGroupConfigById(diCode);
			diCode = impGroupConfig.getCode();
			List<Map<String, Object>> diGroupList = null;
			try {
				diGroupList = fileLookup.lookupGroups(diCode,impGroupConfig.getJkqlx());
			} catch (Exception e) {
				diGroupList = null;
			}
			// 找不到则用本身
			if (null != diGroupList) {
				Map<String, Object> diMap = (Map<String, Object>) diGroupList.get(0).get("files");
				// 找到了一个值.用找到的值
				if (diMap.size() == 1) {
					diCode = DBUtil.getString(diMap.keySet().toArray()[0]);
				} else if (diMap.size() > 1) {// 找到多个值,找相同的
					boolean flag = false;
					for (String key : diMap.keySet()) {
						if (key.equals(diCode)) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						diCode = DBUtil.getString(diMap.keySet().toArray()[0]);
					}

				}
			}
			//lists = this.queryTargetData(clearDate, null, diCode, startRow, rowCount);
			//lists = this.queryTargetData(clearDate, null, diCode, startRow, rowCount);
			lists = this.queryTargetData(productId,zqdm , gddm, sDate , eDate, null, impGroupConfig.getJkqlx()+diCode, startRow, rowCount);
			List<Object> tableField = (List) lists.getData();
			Map<String, String> commentMap = (HashMap<String, String>)tableField.get(1);
			for (Object field : (Object[])tableField.get(0)) {
				if(field != null ){
					GridColumn gridColumn = new GridColumn();
					gridColumn.setHeader(field.toString().equalsIgnoreCase("ST") ? "序号" : commentMap.get(field
							.toString().toUpperCase()));//中文
					gridColumn.setDataIndex(field.toString());//code
					gridColumn.setAlign("center");
					gridColumn.setSortable(true);
					gridColumn.setWidth(100);
					gridColumnList.add(gridColumn);
					StoreField storeField = new StoreField();
					storeField.setType("String");
					storeField.setName(field.toString());//code
					storeField.setMapping(field.toString().equalsIgnoreCase("ST") ? "序号" : commentMap.get(field
							.toString().toUpperCase()));//中文
					storeFieldList.add(storeField);
				}
			}
			
			DataLook dataLook = new DataLook();
			dataLook.setGridColumn(gridColumnList);
			dataLook.setStoreField(storeFieldList);
			String json = JSONUtil.toJson(dataLook);
			AJAXUtil.success(response, json);
		} catch (SOFAServiceException e) {
			AJAXUtil.failure(response, "没有找到表,该接口可能从未读取数据或者表已删除,请检查!");
		}

	}

	/**
	 * 原始数据浏览
	 * 
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 异常
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=dataLookGrid")
	public void dataLookGrid(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException, SQLException {
		Dao dao = importDataBO.getDao(this.getDataSourceMapping());
		String diCode = request.getParameter("diCode");
		List<GridColumn> gridColumnList = new ArrayList<GridColumn>();
		//String clearDate = request.getParameter("clearDate");
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String zqdm = request.getParameter("zqdm");
		String gddm = request.getParameter("gddm");
		String productId = request.getParameter("productId");
		String isDownlOad = request.getParameter("isDownlOad");//用来判断要返回的数据格式,如果是点击快速下载的请求和页面查询的请求返回数据格式不同
		int rowCount = Integer.parseInt(StringUtil.isEmpty(request
				.getParameter(this.COMMON_PARAMS_ROW_COUNT)) ? "30" : request
				.getParameter(this.COMMON_PARAMS_ROW_COUNT));
		int startRow = Integer.parseInt(StringUtil.isEmpty(request
				.getParameter(this.COMMON_PARAMS_START_ROW)) ? "0" : request
				.getParameter(this.COMMON_PARAMS_START_ROW));
		int pageIndex = startRow / rowCount;

		if (StringUtil.isEmpty(diCode) && StringUtil.isEmpty(sDate)&& StringUtil.isEmpty(eDate)) {
			// 不能进行处理
			this.success(response, new ArrayList<String>(), 0);
			return;
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			com.yss.sofa.framework.web.util.Page<Object[]> lists = null;
			ImportDataService importDataService = ServiceUtil.getService(ImportDataService.class);
			ImpGroupConfig impGroupConfig = importDataService.queryImpGroupConfigById(diCode);
			diCode = impGroupConfig.getCode();
			List<Map<String, Object>> diGroupList = null;
			try {
				diGroupList = fileLookup.lookupGroups(diCode,impGroupConfig.getJkqlx());
			} catch (Exception e) {
				diGroupList = null;
			}
			// 找不到则用本身
			if (null != diGroupList) {
				Map<String, Object> diMap = (Map<String, Object>) diGroupList.get(0).get("files");
				// 找到了一个值.用找到的值
				if (diMap.size() == 1) {
					diCode = DBUtil.getString(diMap.keySet().toArray()[0]);
				} else if (diMap.size() > 1) {// 找到多个值,找相同的
					boolean flag = false;
					for (String key : diMap.keySet()) {
						if (key.equals(diCode)) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						diCode = DBUtil.getString(diMap.keySet().toArray()[0]);
					}

				}
			}
			// rowCount+1 因为在结果集中存在第一条记录是表头，占据里一行位置
			// lists = targetData.queryTargetData(clearDate, null, diCode,
			// startRow, rowCount + 1);
			// List<Object[]> tableList = (List<Object[]>) lists.getData();
			// int size = tableList.size();
			// if (size > 1) {
			// for (int i = 1; i < size; i++) {
			// Object[] field = tableList.get(i);
			// Map<String, Object> map = new HashMap<String, Object>();
			// for (int j = 0; j < field.length; j++) {
			// map.put((String) tableList.get(0)[j], field[j]);
			// }
			// mapList.add(map);
			// }
			// }
			// 获取列名和总计数
			//lists = this.queryTargetData(clearDate, null, diCode, startRow, 1);String sDate, String eDate
			lists = this.queryTargetData(productId, zqdm, gddm, sDate, eDate, null, impGroupConfig.getJkqlx()+diCode, startRow, 1);
		//	lists = this.queryTargetData(clearDate, null, diCode, startRow, 1);
			ImpConfig impConfig = importDataService.queryImpConfig(diCode,impGroupConfig.getJkqlx());
			ImpDbConfig dbconfig = impConfig.getImpDbConfig();
			List<String> fields = getDbConfigFields(dbconfig);
			StringBuilder columnName = new StringBuilder();
			
			List<Object> tableField = (List) lists.getData();
			Map<String, String> commentMap = (HashMap<String, String>)tableField.get(1);
			for (Object field : (Object[])tableField.get(0)) {//表头字段属性
				if(field != null){
					GridColumn gridColumn = new GridColumn();
					if (!field.toString().equalsIgnoreCase("ST")) {
						columnName.append(field.toString()).append(",");
					}
					gridColumn.setHeader(field.toString().equalsIgnoreCase("ST") ? "序号" : commentMap.get(field
							.toString().toUpperCase()));//中文
					gridColumn.setDataIndex(field.toString());
					gridColumnList.add(gridColumn);
				}
			}
			columnName.setLength(columnName.length() - 1);
			
			List<SecurityAccount> securityAccountList = importDataBO.queryProSecuAccounts(productId);//获得产品的证券账户信息
			StringBuilder gddms = new StringBuilder();//拼接所选产品的所有股东代码
		    String gdcodes = "";
		    if (ListUtil.isNotEmpty(securityAccountList)) {
			    for (SecurityAccount securityAccount : securityAccountList) {
			    	gddms.append("'"+securityAccount.getCode()+"',");
			     }
			     gdcodes = gddms.substring(0, gddms.length()-1).toString();
		     }
		      
		     StringBuilder sql = new StringBuilder();
		     sql.append("SELECT ").append(StringUtil.joinString(fields)).append(" from  ").append(dbconfig.getTableName());								
		      //sql.append(" WHERE ").append("FIMP_DATE").append(" =  ? ");
		     sql.append(" WHERE ").append(" FIMP_DATE between ").append(" ? ").append(" and ").append(" ? ");
			  //添加查询证券代码条件
		      //添加查询股东代码代码条件
			 String Tzqdm=StringUtil.isEmpty(request.getParameter("Tzqdm"))? DataLookMapperUtil.DATA_MAPPER.get(dbconfig.getTableName()):request.getParameter("Tzqdm");
		     String Tzqzh=StringUtil.isEmpty(request.getParameter("Tgddm"))? DataLookMapperUtil.DATA_MAPPER.get(dbconfig.getTableName()+"1"):request.getParameter("Tgddm");
		     if(StringUtil.isNotEmpty(Tzqdm)&&StringUtil.isNotEmpty(zqdm)) {
		    	  sql.append(" AND ").append(Tzqdm).append(" like '%").append(zqdm).append("%'");
			  }
		     if(StringUtil.isNotEmpty(Tzqzh)&&StringUtil.isNotEmpty(gddm)) {
		    	  sql.append(" AND ").append(Tzqzh).append(" = '").append(gddm).append("'");
			  }
		      //查询所选产品的所有证券账户信息
		     if(StringUtil.isNotEmpty(Tzqzh)&&StringUtil.isNotEmpty(productId)&& StringUtil.isEmpty(gddm) && StringUtil.isNotEmpty(gdcodes)) {
		    	  sql.append(" AND ").append(Tzqzh).append(" in (").append(gdcodes).append(")");
			  }
			sql.append(" AND ").append("FIMP_FILE").append(" LIKE '").append(diCode).append("_%'");
			if(dbconfig.getTableName().toUpperCase().startsWith("T_DI_SCB_")){
				sql.append(" order by ferror_code desc,ferror_name desc ");
			}
			//获取所查到的数据的个数(分页查询的总条数)           wyx  2018-03-01
			List<Object[]> getTotalCount = dao.findBySql("select count(1) from ("+sql.toString()+")temp ", new Object[] { sDate,eDate });
			int totalCount=0;
			if(ListUtil.isNotEmpty(getTotalCount)){   //查出数据总条数赋值给page的total字段
			    totalCount=DBUtil.getInt(getTotalCount.get(0));
			}
			DBUtil.setDbType(dao.getCurrentDBType());
			String result = DBUtil.getFySql(sql.toString(), rowCount, pageIndex);
			List<Object[]> tableList = new ArrayList<Object[]>();
			//tableList.addAll(dao.findBySql(result.toString()+DBUtil.NO_TRANSFER, new Object[] { clearDate }));
			tableList.addAll(dao.findBySql(result+DBUtil.NO_TRANSFER, new Object[] { sDate , eDate }));
			//如果证券代码为空
			//如果证券代码为空
		      if(StringUtil.isEmpty(Tzqdm) && StringUtil.isNotEmpty(zqdm)){
		    	  tableList.clear();
		      }
		      if(StringUtil.isEmpty(Tzqzh) && StringUtil.isNotEmpty(gddm)){
		    	  tableList.clear();
		      }
			int size = tableList.size();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					Object[] field = tableList.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					//map.put(gridColumnList.get(0).getDataIndex() , i+1);
					for (int j = 0; j < field.length; j++) {
						if(StringUtil.isNotEmpty(isDownlOad) && "true".equalsIgnoreCase(isDownlOad)){
							map.put(gridColumnList.get(j).getDataIndex(), field[j]);//前台页面快速下载返回的数据格式
						}else{
							map.put(gridColumnList.get(j).getHeader(), field[j]);//前台页面展示请求返回的数据格式
						}
						/*if(j==field.length-1){
							map.put(gridColumnList.get(field.length-1).getDataIndex(), i+1);
						}*/
					}
					mapList.add(map);
				}
			}
			this.success(response, mapList, totalCount);
		} catch (SOFAServiceException e) {
			AJAXUtil.failure(response, e.getMessage());
		}
	}

	private List<String> getDbConfigFields(ImpDbConfig dbconfig) {
		List<TableFieldConfig> tableFields = dbconfig.getTableFields();
		List<String> fields = new ArrayList<String>();
		fields.add("FIMP_DATE");
		fields.add("FIMP_FILE");
		fields.add(TableFieldConfig.getReadFlagTableFieldConfig().getColumnName().toUpperCase());
		fields.add(TableFieldConfig.getImpFileNameTableFieldConfig().getColumnName().toUpperCase());
		//	有些原始表没有这个删除字段，所以先注释掉。	zhongtao	20200118
		//	BUG #351710 长沙【公司内部】【托管系统5.0_核算】【自动化】【数据浏览】数据浏览查询证券基本信息接口报错
//		fields.add(TableFieldConfig.getDeleteTableFieldConfig().getColumnName().toUpperCase());
		//BUG #230724 北京【公司内部】【托管系统5.0_清算】【数据浏览】 gzj 20181126
		for (TableFieldConfig tableFieldConfig : tableFields) {
			if(!fields.contains(tableFieldConfig.getColumnName().toUpperCase())){
				fields.add(tableFieldConfig.getColumnName().toUpperCase());
			}
		}
		return fields;
	}

	/**
	 * @param request servlet请求对象
	 * @param response servlet响应对象
	 * @throws SOFAException 异常信息
	 * @return String
	 */
	@RequestMapping(params = "method=toDataLook")
	public String toDataLook(HttpServletRequest request, HttpServletResponse response) {

		return "dataLook";
	}
	/**
	 * 获取进度条
	 * 
	 * @param request
	 * @param response
	 * @throws SOFAException
	 */
	@RequestMapping(params = "method=getProgressBar")
	public void getProgressBar(HttpServletRequest request, HttpServletResponse response)
			throws SOFAException {

		String importDataProgressBar = DBUtil.getString(SOFAContext.getInstance().getAttribute(
				"importDataProgressBar"));
		
		AJAXUtil.success(
				response,
				StringUtil.isEmpty(importDataProgressBar) ? "0" : DBUtil.getString(Double
						.valueOf(importDataProgressBar) / 100));

	}

	/**
	 * 根据接口名称和清算日期删除数据
	 * 
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws BizException 异常信息
	 */
	@RequestMapping(params = "method=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws BizException {
		String diCo = request.getParameter("diCode");
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String zqdm = request.getParameter("zqdm");
		String gddm = request.getParameter("gddm");
		//BUG #307218 深圳【平安银行】【托管系统5.0_核算】【数据读取】根据产品名称或股东代码筛选之后，点删除，删该所有上交所数据；同时选取多个产品，数据读取之后，上交所过户文件翻倍；文件命名规则中“gh*”无法保存。
		String productId = request.getParameter("productId");
		List<SecurityAccount> securityAccountList = importDataBO.queryProSecuAccounts(productId);//获得产品的证券账户信息
		StringBuilder gddms = new StringBuilder();//拼接所选产品的所有股东代码
	    String gdcodes = "";
	    if (ListUtil.isNotEmpty(securityAccountList)) {
		    for (SecurityAccount securityAccount : securityAccountList) {
		    	gddms.append("'"+securityAccount.getCode()+"',");
		     }
		     gdcodes = gddms.substring(0, gddms.length()-1).toString();
	     }
		try {
			//targetData.delTargetData(clearDate, null, diCode.substring(0, diCode.indexOf("|")));
			Dao dao = importDataBO.getDao(getDataSourceMapping());
			ImportDataService importDataService = ServiceUtil.getService(ImportDataService.class);
			ImpGroupConfig impGroupConfig = importDataService.queryImpGroupConfigById(diCo);
			String diCode = impGroupConfig.getCode();
			ImpConfig impConfig = importDataService.queryImpConfig(diCode,impGroupConfig.getJkqlx());
			ImpDbConfig dbconfig = impConfig.getImpDbConfig();
			//List<Object> params = new ArrayList<Object>();
			StringBuilder sql= new StringBuilder();
			sql.append("delete from ").append(dbconfig.getTableName());
			sql.append(" WHERE ").append(" FIMP_DATE between ").append(" ? ").append(" and ").append(" ? ");
			String Tzqdm= DataLookMapperUtil.DATA_MAPPER.get(dbconfig.getTableName());
		    String Tzqzh=DataLookMapperUtil.DATA_MAPPER.get(dbconfig.getTableName()+"1");
    	      if(StringUtil.isNotEmpty(Tzqdm)&&StringUtil.isNotEmpty(zqdm)) {
    	    	  sql.append(" AND ").append(Tzqdm).append(" = '").append(zqdm).append("'");
    	      }
    	      if(!StringUtil.isNotEmpty(Tzqzh) && StringUtil.isNotEmpty(gddm)) {
    	    	  sql.append(" AND ").append(Tzqzh).append(" = '").append(gddm).append("'");
    	      }
		      //删除条件 所选产品的所有证券账户信息
		      if(StringUtil.isNotEmpty(Tzqzh) && StringUtil.isNotEmpty(productId) && StringUtil.isEmpty(gddm) && StringUtil.isNotEmpty(gdcodes)) {
		    	  sql.append(" AND ").append(Tzqzh).append(" in (").append(gdcodes).append(")");
			  }
			 //ta的数据都是读到一张原始表里，要做特殊处理;qd应的接口代码是在境内的接口后面加上“_qd”,删除境内的需要剔除qd的数据
		    if(diCode.startsWith("ta_")){
		    	if(diCode.contains("_qd")){
		    		sql.append(" and ").append(StringUtil.createlikeSql(" fimp_file ",diCode.split(",")));
		    	}else{
		    		sql.append(" and ").append(StringUtil.createlikeSql(" fimp_file ",diCode.split(","))).append(" and fimp_file not like '%qd%'");
		    	}
		    	dao.executeBySql(sql.toString(), new Object[]{ sDate , eDate,diCode+"%" });
		    }else{
		    	dao.executeBySql(sql.toString(), new Object[]{ sDate , eDate });
		    }
			AJAXUtil.success(response, "删除成功！");
		} catch (Exception e) {
			AJAXUtil.failure(response, e.getMessage());
		}
	}
	
	
	
	
	/**
	 * 根据接口名称和清算日期批量删除数据
	 * 
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws BizException 异常信息
	 */
	@RequestMapping(params = "method=batchDelData")
	public void batchDelData(HttpServletRequest request, HttpServletResponse response)
			throws BizException{
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String diCode = request.getParameter("diCode");
		Dao dao = importDataBO.getDao(getDataSourceMapping());
		ImportDataService importDataService = ServiceUtil.getService(ImportDataService.class);
		String[] codeList = diCode.split(",");
		Connection conn = null;
		Session session = null;
		PreparedStatement prepareStatement = null;
		try {
			session = HibernateUtil.getSession(dao);
			conn = session.connection();
			conn.setAutoCommit(false);
			StringBuilder sql= new StringBuilder();
			
			
		for (int i = 0; i < codeList.length; i++) {
			ImpGroupConfig impGroupConfig = importDataService.queryImpGroupConfigById(codeList[i]);
			String Code = impGroupConfig.getCode();
			ImpConfig impConfig = importDataService.queryImpConfig(Code,impGroupConfig.getJkqlx());
			ImpDbConfig dbconfig = impConfig.getImpDbConfig();
			sql.setLength(0);
			sql.append(" delete from ").append(dbconfig.getTableName());
			sql.append(" WHERE ").append(" FIMP_DATE between ").append(" ? ").append(" and ").append(" ? ");
			sql.append(" AND ").append("FIMP_FILE").append(" LIKE ?");
			prepareStatement = conn.prepareStatement(sql.toString());
			
			
				
//			prepareStatement.setString(1, dbconfig.getTableName());
			prepareStatement.setString(1, sDate);
			prepareStatement.setString(2, eDate);
			prepareStatement.setString(3, Code+"_%");
			prepareStatement.execute();
			
			sql.setLength(0);
			sql.append(" delete from T_DI_RESULT where (FDATE between ? and ?) and FIMP_GROUP=?");
			prepareStatement = conn.prepareStatement(sql.toString());
			prepareStatement.setDate(1, new java.sql.Date(DateUtil.stringtoDate(sDate, "yyyy-MM-dd").getTime()));
			prepareStatement.setDate(2, new java.sql.Date(DateUtil.stringtoDate(eDate, "yyyy-MM-dd").getTime()));
			prepareStatement.setString(3, Code);
			prepareStatement.execute();
		}
		conn.commit();
		AJAXUtil.success(response, "删除成功！");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			    log.error("数据库回滚出错" + e1.getMessage());
			}
			AJAXUtil.failure(response, ExceptionCodeConstant.IMPGROUPCONFIG_306P);
		} finally{
			DBUtil.closeStatement(prepareStatement);
			DBUtil.closeConnection(conn);
			DBUtil.closeSession(session);
		}
	}
	
	/**
	 * 执行sql语句
	 * @param dao
	 * @param sql
	 * @param sDate
	 * @param eDate
	 */
	public void executeBysql(Dao dao,String sql,String sDate,String eDate){
		dao.executeBySql(sql, new Object[]{ sDate , eDate });
	}
	
	   /**
     * 根据条件查询产品专用的证券账户信息
     * 
     * @param request
     *            Http请求对象
     * @param response
     *            Http相应对象
     * @author luoyu
     * @date 2018-12-11
     */
    @RequestMapping(params = "method=queryProSecuAccounts")
    public void queryProSecuAccounts(HttpServletRequest request, HttpServletResponse response){
        String productId = request.getParameter("productId");
        List<SecurityAccount> securityAccountList;
        try{
        	securityAccountList = importDataBO.queryProSecuAccounts(productId);
            AJAXUtil.success(response, securityAccountList);
        }catch(Exception e){
            AJAXUtil.failure(response,e);
        }
    }
    
	   /**
      * 根据ID条件查询接口群信息
      * 
      * @param request
      *            Http请求对象
      * @param response
      *            Http相应对象
      * @author hb
      * @date 2019-5-13
      */
     @RequestMapping(params = "method=queryDicodeById")
     public void queryDicodeById(HttpServletRequest request, HttpServletResponse response){
         String id = request.getParameter("id");
         String code;
         try{
        	 code = importDataBO.queryDicodeById(id);
             AJAXUtil.success(response, code);
         }catch(Exception e){
             AJAXUtil.failure(response,e);
         }
     }
     
     /**
      * 	所有有效的产品接口配置code
      * 
      * 
     * @param request
     * @param response
     * 
     * @author  zhongtao
     * @date    2021-3-8
     * @Description 方法详细说明，包括用途、注意事项、举例说明等。
     */
    @RequestMapping(params = "method=queryAllProductDiConfig")
     public void queryAllProductDiConfig(HttpServletRequest request, HttpServletResponse response){
    		 List<String> codes = importConfigBO.queryAllProductDiConfig();
    		 AJAXUtil.success(response, JSONUtil.toJson(codes));
     }	
}
