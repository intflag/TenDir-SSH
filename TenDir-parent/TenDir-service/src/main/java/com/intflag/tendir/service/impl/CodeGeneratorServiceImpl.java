package com.intflag.tendir.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intflag.tendir.dao.ICodeGeneratorDao;
import com.intflag.tendir.dao.IResourceDao;
import com.intflag.tendir.entity.Resource;
import com.intflag.tendir.entity.TableFields;
import com.intflag.tendir.entity.TemplateParams;
import com.intflag.tendir.service.ICodeGeneratorService;
import com.intflag.tendir.utils.FileHandle;
import com.intflag.tendir.utils.MapToBeanUtils;
import com.intflag.tendir.utils.PbUtils;
import com.intflag.tendir.utils.TemplateHelp;
import com.intflag.tendir.utils.TenDirUtils;

import freemarker.template.TemplateException;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年6月13日 上午8:27:16
 * @Description 代码生成器业务层接口实现
 * @version V1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
@Transactional
public class CodeGeneratorServiceImpl implements ICodeGeneratorService {

	@Autowired
	private ICodeGeneratorDao codeGeneratorDao;
	
	@Autowired
	private IResourceDao resourceDao;

	private static String database = "tendir";

	@Override
	public List<TemplateParams> findAllTable() throws Exception {
		String sql = "select TABLE_NAME  as TABLENAME  from information_schema.TABLES t where t.TABLE_SCHEMA=:database";
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("database", database);
		List<Map> maps = codeGeneratorDao.findBySql(sql, params);
		MapToBeanUtils utils = new MapToBeanUtils<TemplateParams>();
		List<TemplateParams> l = utils.ListMapToJavaBean(maps, TemplateParams.class);
		return l;
	}

	@Override
	public List<TableFields> findFieldByTableName(TemplateParams model) throws Exception {
		List<Map> maps = getListMap(model.getTableName());
		MapToBeanUtils utils = new MapToBeanUtils<TableFields>();
		List<TableFields> l = utils.ListMapToJavaBean(maps, TableFields.class);
		return l;
	}

	/**
	 * 获取列数据
	 * 
	 * @param templateParams
	 * @return
	 */
	private List<Map> getListMap(String tableName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("table_name", tableName);// 表名称
		params.put("database", database);// 数据库

		String sql = "SELECT COLUMN_NAME COLUMNNAME,COLUMN_TYPE COLUMNTYPE,COLUMN_KEY COLUMNKEY ,CHARACTER_MAXIMUM_LENGTH DATALENGTH, ";
		sql += " IS_NULLABLE ISNULLABLE,COLUMN_COMMENT COLUMNCOMMENT FROM INFORMATION_SCHEMA.COLUMNS T ";
		sql += " WHERE TABLE_NAME = :table_name ";
		sql += " AND T.TABLE_SCHEMA=:database";

		List<Map> maps = codeGeneratorDao.findBySql(sql, params);
		return maps;
	}

	@Override
	public boolean createCode(TemplateParams templateParams, HttpSession session, HttpServletRequest request)
			throws Exception {
		String jspFilePath = templateParams.getClassPath() + "/TenDir-web/src/main/webapp/WEB-INF/pages/app/"
				+ templateParams.getClassNames() + "/";
		FileHandle.mkdirs(jspFilePath);// 创建目录
		//File f = new File(jspFilePath + templateParams.getFunctionComment() + ".txt");// 创建txt文件
		//if (!f.exists()) {
			//f.createNewFile();
		//}

		// jspFilePath += templateParams.getClassNames();

		// 1、生成Model代码
		creatModel(templateParams, request);

		// 生成hbm配置文件
		createModelHbm(templateParams, request);

		// 2、生成Add.jsp
		String addFile = "app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Add";
		String addFileNm = jspFilePath + "add.jsp";
		AddAndEditPubMeth("/LayUIFtl/AddJsp.ftl", templateParams, addFile, addFileNm, request);

		// 3、生成form.jsp
		String editFile = "app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Edit";
		String editFileNm = jspFilePath + "form.jsp";
		AddAndEditPubMeth("/LayUIFtl/FormJsp.ftl", templateParams, editFile, editFileNm, request);

		// 4、生成js
		String searchFile = "app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Search";
		String jspFilePath2 = templateParams.getClassPath() + "/TenDir-web/src/main/webapp/statics/js/app/"
				+ templateParams.getClassNames() + "/";
		FileHandle.mkdirs(jspFilePath2);// 创建目录
		String searchFileNm = jspFilePath2 + (templateParams.getClassNames()).toLowerCase() + ".js";
		AddAndEditPubMeth("/LayUIFtl/Js.ftl", templateParams, searchFile, searchFileNm, request);

		// 5.生成list.jsp
		String listFile = "app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "List";
		String ListFileNm = jspFilePath + "list.jsp";
		createListJsp(templateParams, listFile, ListFileNm, request);

		// 6.生成serviceImpl
		creatserviceImpl(templateParams, request);

		// 7.生成Controller类
		creatAction(templateParams, request);

		// 8.生成Iservice接口类
		String IServicefileName = templateParams.getClassPath()
				+ "/TenDir-service/src/main/java/com/intflag/tendir/service/" + "I" + templateParams.getClassNames()
				+ "Service.java";// 生成文件的路径和名称
		creatIServiceAndIDao(templateParams, "/LayUIFtl/IService.ftl", IServicefileName, request);

		// 9.生成IDao类
		String IDaofileName = templateParams.getClassPath() + "/TenDir-dao/src/main/java/com/intflag/tendir/dao/" + "I"
				+ templateParams.getClassNames() + "Dao.java";// 生成文件的路径和名称
		creatIServiceAndIDao(templateParams, "/LayUIFtl/IDao.ftl", IDaofileName, request);

		// 10.生成DaoImpl类型
		String IDaoImplfileName = templateParams.getClassPath() + "/TenDir-dao/src/main/java/com/intflag/tendir/dao/impl/"
				+ templateParams.getClassNames() + "DaoImpl.java";// 生成文件的路径和名称
		creatIServiceAndIDao(templateParams, "/LayUIFtl/IDaoImpl.ftl", IDaoImplfileName, request);

		// 11.是否自动配置菜单
		if (!PbUtils.isEmpty(templateParams.getAddmenu()) && templateParams.getAddmenu().equals("1")) {
			if (StringUtils.isNotBlank(templateParams.getPid())) {
				addMenu(templateParams);
			}
		}

		// 12.生成查看页面
		//String detailsFile = "app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Search";
		//String detailsFileNm = jspFilePath + "Detail.jsp";
		//AddAndEditPubMeth("/LayUIFtl/DetailsJsp.ftl", templateParams, detailsFile, detailsFileNm, request);

		return true;
	}

	private void addMenu(TemplateParams templateParams) {
		String pid = templateParams.getPid();
		Resource parentResource = new Resource();
		parentResource.setResourceId(pid);
		Resource resource = new Resource();
		resource.setParentResource(parentResource);//设置上级菜单
		resource.setResname(templateParams.getFunctionComment());//设置菜单名称
		resource.setResurl("page_app_"+PbUtils.strRelplacetoLowerCase(templateParams.getClassNames())+"_list.action");//设置菜单URL
		resource.setRescode("pageQuery");//授权标识
		resource.setIcon("fa fa-adjust");//图标
		resource.setType("1");//类型
		resource.setDescription(templateParams.getFunctionComment());//描述
		resource.setFlag("1");//标记
		resource.setSort(1);//排序
		resource.setCdate(new Timestamp(System.currentTimeMillis()));//创建时间
		resource.setMdate(new Timestamp(System.currentTimeMillis()));//修改时间
		resourceDao.save(resource);
	}


	/**
	 * 生成实体hibernate配置文件
	 * 
	 * @param templateParams
	 * @param request
	 * @throws Exception
	 * @throws IOException
	 */
	private void createModelHbm(TemplateParams templateParams, HttpServletRequest request)
			throws IOException, Exception {
		List<Map> list = getListMap(templateParams.getTableName());
		List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();

		String entityId = "";
		String tableId = "";
		boolean flag = false;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> oMap = new HashMap<String, Object>();
				// 遍历list
				Map<String, Object> map = list.get(i);
				// 遍历map
				for (String key : map.keySet()) {
					// 列名称
					if (key.equals("COLUMNNAME")) {
						String columnName = map.get(key).toString();// 列名称
						String reStr = PbUtils.strRelplacetoLowerCase(columnName);// 列名称，首字母小写，去下划线
						// 判断是否为主键
						if ((columnName.toUpperCase()).equals(templateParams.getPkColumn().toUpperCase())) {
							entityId = reStr;
							tableId = columnName;
							flag = true;
							continue;
						}
						oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
					}
				}
				if (flag) {
					flag = !flag;
				} else {
					clList.add(oMap);// 添加到集合
				}
			}
		}

		if (clList != null && clList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("className", templateParams.getClassNames());// 类名称
			data.put("cloums", clList);// 属性
			data.put("date", PbUtils.getCurrentDate());// 日期
			data.put("entityId", entityId);// 实体id
			data.put("tableId", tableId);// 数据库id
			// 作者
			if (!PbUtils.isEmpty(templateParams.getAuthor())) {
				data.put("author", templateParams.getAuthor());
			}
			data.put("Table_NAME", templateParams.getTableName());// 表名称
			// 模板名称
			String ftlName = "/LayUIFtl/Hbm.ftl";

			String fileName = templateParams.getClassPath() + "/TenDir-entity/src/main/java/com/intflag/tendir/entity/"
					+ templateParams.getClassNames() + ".hbm.xml";
			TemplateHelp.creatTemplate(data, ftlName, fileName, request);
		}
	}

	/**
	 * 生成IService类、IDao类
	 * 
	 * @param templateParams
	 * @param ftlname
	 *            ftl文件名称
	 * @param fileName
	 *            生成文件的全路径
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void creatIServiceAndIDao(TemplateParams templateParams, String ftlname, String fileName,
			HttpServletRequest request) throws IOException, TemplateException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("className", templateParams.getClassNames());// JavaBean名称
		data.put("classNameToL", PbUtils.fristStrToLowerCase(templateParams.getClassNames()));// JavaBean名称-第一个字母小写
		data.put("HiTable", PbUtils.fristStrToUpperCase(PbUtils.strRelplacetoLowerCase(templateParams.getTableName())));// hibbernate
		// 作者
		if (!PbUtils.isEmpty(templateParams.getAuthor())) {
			data.put("author", templateParams.getAuthor());
		}
		// 对象
		data.put("functionComment", templateParams.getFunctionComment());// 功能说明
		data.put("date", PbUtils.getCurrentDate());// 日期
		TemplateHelp.creatTemplate(data, ftlname, fileName, request);
	}

	/**
	 * 生成Action类
	 * 
	 * @param templateParams
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void creatAction(TemplateParams templateParams, HttpServletRequest request)
			throws IOException, TemplateException {
		List<Map> list = getListMap(templateParams.getTableName());
		List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();

		String entityId = "";
		String tableId = "";
		boolean flag = false;
		String sqlselect = "select ";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> oMap = new HashMap<String, Object>();
				// 遍历list
				Map<String, Object> map = list.get(i);

				// 遍历map
				for (String key : map.keySet()) {

					// 列名称
					if (key.equals("COLUMNNAME")) {
						/////
						String columnName = map.get(key).toString();// 列名称
						String reStr = PbUtils.strRelplacetoLowerCase(map.get(key).toString());// 列名称，首字母小写，去下划线
						oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
						oMap.put("columnNameBySql", map.get(key).toString());// 列名称,sql使用

						// 自动判断大小写
						if (map.get(key).toString().substring(1, 2).equals("_")) {
							oMap.put("UpUmnName", reStr);// 列名称，首字母小写，去下划线
						} else {
							oMap.put("UpUmnName", PbUtils.fristStrToUpperCase(reStr));// 列名称，首字母大写，去下划线

						}

						if (i == (list.size() - 1)) {
							sqlselect += "t." + map.get(key).toString() + " as " + reStr;
						} else {
							sqlselect += "t." + map.get(key).toString() + " as " + reStr + ",";
						}

						// 判断是否为主键
						if ((columnName.toUpperCase()).equals(templateParams.getPkColumn().toUpperCase())) {
							entityId = reStr;
							tableId = columnName;
							flag = true;
							continue;
						}
					}

					// 注释
					if (key.equals("COLUMNCOMMENT")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get("COLUMNNAME").toString());// 列名称，首字母小写，去下划线
						oMap.put("columnComment", map.get(key) == null ? reStr : map.get(key));// 注释
					}

				}
				if (flag) {
					flag = !flag;
				} else {
					clList.add(oMap);// 添加到集合
				}
			}
		}
		if (clList != null && clList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("cloums", clList);// 属性
			data.put("entityId", entityId);// 实体id
			data.put("tableId", tableId);// 数据库id
			data.put("filePath", templateParams.getFilePath());// 文件路径,定义在方法和Id前，例如sys_menuInf_MenuInfList
			data.put("action", PbUtils.fristStrToLowerCase(templateParams.getClassNames()));// action名称-第一个字母小写
			data.put("Action", PbUtils.fristStrToUpperCase(templateParams.getClassNames()));// 第一个字母大写
			data.put("pkColumn",
					PbUtils.fristStrToLowerCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));// 列名称，首字母大写，去下划线
			data.put("PkColumn",
					PbUtils.fristStrToUpperCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));// 列名称，首字母大写，去下划线
			data.put("oldpkColumn", templateParams.getPkColumn());// 主键字段
			data.put("sortColumn", templateParams.getSortColumn());// 排序字段
			data.put("HiTable",
					PbUtils.fristStrToUpperCase(PbUtils.strRelplacetoLowerCase(templateParams.getTableName())));// hibbernate
			data.put("functionComment", templateParams.getFunctionComment());// 功能说明
			data.put("date", PbUtils.getCurrentDate());// 日期
			data.put("tableName", templateParams.getTableName());// 表名称
			data.put("sqlselect", sqlselect);// sql查询语句
			data.put("PkColumnBySql", templateParams.getPkColumn());// 主键字段
			// 作者
			if (!PbUtils.isEmpty(templateParams.getAuthor())) {
				data.put("author", templateParams.getAuthor());
			}
			// 生成文件的路径和名称
			// String fileName = templateParams.getClassPath() + "/" +
			// templateParams.getClassNames() + "ServiceImpl.java";
			String fileName = templateParams.getClassPath() + "/TenDir-web/src/main/java/com/intflag/tendir/web/action/"
					+ templateParams.getClassNames() + "Action.java";

			// ftl文件名称
			String ftlname = "/LayUIFtl/controller.ftl";
			TemplateHelp.creatTemplate(data, ftlname, fileName, request);
			//生成struts.xml配置
			String strutsXmlComment = "&lt!-- "+templateParams.getFunctionComment()+" -->";
			String actionStr = PbUtils.fristStrToLowerCase(templateParams.getClassNames());
			String strutsXml1 = "&ltaction name=\""+actionStr+"Action_*\" class=\""+actionStr+"Action\" method=\"{1}\"&gt";
			String strutsXml2 = "	&ltresult name=\"edit\"&gt/WEB-INF/pages/app/"+actionStr+"/form.jsp&lt/result&gt";
			String strutsXml3 = "&lt/action&gt";
			String codeStrutsXml = strutsXmlComment+"\r\n"+strutsXml1+"\r\n"+strutsXml2+"\r\n"+strutsXml3;
			TenDirUtils.getSession().setAttribute("codeStrutsXml", codeStrutsXml);
		}

	}

	/**
	 * 生成serviceImpl.java类
	 * 
	 * @param templateParams
	 * @throws TemplateException
	 * @throws IOException
	 */
	private void creatserviceImpl(TemplateParams templateParams, HttpServletRequest request)
			throws IOException, TemplateException {
		List<Map> list = getListMap(templateParams.getTableName());
		List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();

		String entityId = "";
		String tableId = "";
		boolean flag = false;
		String sqlselect = "select ";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> oMap = new HashMap<String, Object>();
				// 遍历list
				Map<String, Object> map = list.get(i);

				// 遍历map
				for (String key : map.keySet()) {

					// 列名称
					if (key.equals("COLUMNNAME")) {
						/////
						String columnName = map.get(key).toString();// 列名称
						String reStr = PbUtils.strRelplacetoLowerCase(map.get(key).toString());// 列名称，首字母小写，去下划线
						oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
						oMap.put("columnNameBySql", map.get(key).toString());// 列名称,sql使用

						// 自动判断大小写
						if (map.get(key).toString().substring(1, 2).equals("_")) {
							oMap.put("UpUmnName", reStr);// 列名称，首字母小写，去下划线
						} else {
							oMap.put("UpUmnName", PbUtils.fristStrToUpperCase(reStr));// 列名称，首字母大写，去下划线

						}

						if (i == (list.size() - 1)) {
							sqlselect += "t." + map.get(key).toString() + " as " + reStr;
						} else {
							sqlselect += "t." + map.get(key).toString() + " as " + reStr + ",";
						}

						// 判断是否为主键
						if ((columnName.toUpperCase()).equals(templateParams.getPkColumn().toUpperCase())) {
							entityId = reStr;
							tableId = columnName;
							flag = true;
							continue;
						}
					}

					// 注释
					if (key.equals("COLUMNCOMMENT")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get("COLUMNNAME").toString());// 列名称，首字母小写，去下划线
						oMap.put("columnComment", map.get(key) == null ? reStr : map.get(key));// 注释
					}

				}
				if (flag) {
					flag = !flag;
				} else {
					clList.add(oMap);// 添加到集合
				}
			}
		}
		if (clList != null && clList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("cloums", clList);// 属性
			data.put("entityId", entityId);// 实体id
			data.put("EntityId", PbUtils.fristStrToUpperCase(entityId));// 实体id
			data.put("tableId", tableId);// 数据库id
			data.put("filePath", templateParams.getFilePath());// 文件路径,定义在方法和Id前，例如sys_menuInf_MenuInfList
			data.put("action", PbUtils.fristStrToLowerCase(templateParams.getClassNames()));// action名称-第一个字母小写
			data.put("Action", PbUtils.fristStrToUpperCase(templateParams.getClassNames()));// 第一个字母大写
			// data.put("pkColumn",
			// PbUtils.fristStrToLowerCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));//
			// 主键字段-第一字母小写，去掉下划线，下划线紧跟的字母大写
			// data.put("PkColumn",
			// PbUtils.fristStrToUpperCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));//
			// 主键字段-第一字母大写，去掉下划线，下划线紧跟的字母大写
			data.put("pkColumn",
					PbUtils.fristStrToLowerCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));// 列名称，首字母大写，去下划线
			data.put("PkColumn",
					PbUtils.fristStrToUpperCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));// 列名称，首字母大写，去下划线
			data.put("oldpkColumn", templateParams.getPkColumn());// 主键字段
			data.put("sortColumn", templateParams.getSortColumn());// 排序字段
			data.put("HiTable",
					PbUtils.fristStrToUpperCase(PbUtils.strRelplacetoLowerCase(templateParams.getTableName())));// hibbernate
			data.put("functionComment", templateParams.getFunctionComment());// 功能说明
			data.put("date", PbUtils.getCurrentDate());// 日期
			data.put("tableName", templateParams.getTableName());// 表名称
			data.put("sqlselect", sqlselect);// sql查询语句
			data.put("PkColumnBySql", templateParams.getPkColumn());// 主键字段
			// 作者
			if (!PbUtils.isEmpty(templateParams.getAuthor())) {
				data.put("author", templateParams.getAuthor());
			}
			// 生成文件的路径和名称
			// String fileName = templateParams.getClassPath() + "/" +
			// templateParams.getClassNames() + "ServiceImpl.java";
			String fileName = templateParams.getClassPath()
					+ "/TenDir-service/src/main/java/com/intflag/tendir/service/impl/" + templateParams.getClassNames()
					+ "ServiceImpl.java";

			String ftlname = "";
			// ftl文件名称
			ftlname = "/LayUIFtl/ServiceImplBySql.ftl";
			TemplateHelp.creatTemplate(data, ftlname, fileName, request);
		}

	}

	/**
	 * 生成List.jsp 代码
	 * 
	 * @param templateParams
	 * @param filePath
	 *            Id名称，例如sys_menuInf_MenuInfList
	 * @param fileName
	 *            文件生成的路径 例如 c://Add.jsp
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void createListJsp(TemplateParams templateParams, String filePath, String fileName,
			HttpServletRequest request) throws IOException, TemplateException {
		List<Map> list = getListMap(templateParams.getTableName());
		List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> oMap = new HashMap<String, Object>();
				// 遍历list
				Map<String, Object> map = list.get(i);

				// 遍历map
				for (String key : map.keySet()) {

					// 列名称
					if (key.equals("COLUMNNAME")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get(key).toString());// 列名称，首字母小写，去下划线
						// if (up) {
						// oMap.put("columnName",
						// PbUtils.fristStrToUpperCase(reStr));// 列名称，首字母大写，去下划线
						// } else if (listIsLower) {
						// oMap.put("columnName",
						// PbUtils.fristAndSecondStrToLowerCase(reStr));//
						// 列名称，首字母小写,第二个字母小写，去下划线
						// } else {
						// oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
						// }
						oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
						oMap.put("UpUmnName", PbUtils.fristStrToUpperCase(reStr));// 列名称，首字母大写，去下划线
					}

					// 注释
					if (key.equals("COLUMNCOMMENT")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get("COLUMNNAME").toString());// 列名称，首字母小写，去下划线
						oMap.put("columnComment", map.get(key) == null ? reStr : map.get(key));// 注释
					}

				}
				clList.add(oMap);// 添加到集合
			}
		}

		if (clList != null && clList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("classNameUp", templateParams.getClassNames());// 类名称
			data.put("classNameLow", (templateParams.getClassNames()).toLowerCase());// 类名称
			data.put("cloums", clList);// 属性
			data.put("functionComment", templateParams.getFunctionComment());// 功能说明
			data.put("filePath", filePath);// 文件路径,定义在方法和Id前，例如sys_menuInf_MenuInfList
			data.put("action", PbUtils.fristStrToLowerCase(templateParams.getClassNames()));// action名称
			data.put("Action", PbUtils.fristStrToUpperCase(templateParams.getClassNames()));// 第一个字母大写
			data.put("pkColumn", templateParams.getPkColumn());// 主键字段
			data.put("sortColumn", templateParams.getSortColumn());// 排序字段
			data.put("pkColumnDel",
					PbUtils.fristStrToLowerCase(PbUtils.strRelplacetoLowerCase(templateParams.getPkColumn())));// 主键字段
			// data.put("pkColumn_f",
			// PbUtils.strRelplacetoLowerCase(templateParams.getSortColumn()));//
			// 主键字段
			data.put("searchFilePath",
					"app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Search");// List页面的searchFilePath属性
			data.put("addFilePath",
					"app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Add");// List页面的addFilePath属性
			data.put("editFilePath",
					"app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "Edit");// List页面的editFilePath属性
			TemplateHelp.creatTemplate(data, "/LayUIFtl/List.ftl", fileName, request);
		}

	}

	/**
	 * 生成JavaBean代码
	 * 
	 * @param templateParams
	 * @throws TemplateException
	 * @throws IOException
	 */
	private void creatModel(TemplateParams templateParams, HttpServletRequest request) throws Exception {
		List<Map> list = getListMap(templateParams.getTableName());
		List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();
		// List<Map> isNullList =
		// getIsNullAble(templateParams.getTableName().toUpperCase());

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> oMap = new HashMap<String, Object>();
				// 遍历list
				Map<String, Object> map = list.get(i);

				// 遍历map
				for (String key : map.keySet()) {
					// 列名称
					if (key.equals("COLUMNNAME")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get(key).toString());// 列名称，首字母小写，去下划线
						oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线

						// 自动判断大小写
						if (map.get(key).toString().substring(1, 2).equals("_")) {
							oMap.put("UpUmnName", reStr);// 列名称，首字母小写，去下划线
						} else {
							oMap.put("UpUmnName", PbUtils.fristStrToUpperCase(reStr));// 列名称，首字母大写，去下划线
						}

					}

					// 注释
					if (key.equals("COLUMNCOMMENT")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get("COLUMNNAME").toString());// 列名称，首字母小写，去下划线
						oMap.put("columnComment", map.get(key) == null ? reStr : map.get(key));// 注释
					}

					// 列类型
					if (key.equals("COLUMNTYPE")) {
						String columnType = map.get("COLUMNTYPE").toString();// 列类型
						String columnName = map.get("COLUMNNAME").toString();// 列名称
						String IsNullAble = map.get("ISNULLABLE").toString();// 是否为空

						String dataLength = "10";
						if (map.containsKey("DATALENGTH") && map.get("DATALENGTH") != null) {
							dataLength = map.get("DATALENGTH").toString();// 长度
						}
						String cloums_top = "";

						// 判断是否为主键
						if ((columnName.toUpperCase()).equals(templateParams.getPkColumn().toUpperCase())) {
							cloums_top += "@Id ";
						}
						if (columnType.contains("timestamp")) {
							oMap.put("cloumsType", "Timestamp");
						}
						if (columnType.contains("long") || columnType.contains("interval")
								|| columnType.contains("blob") || columnType.contains("varchar")
								|| columnType.contains("char")) {
							oMap.put("cloumsType", "String");
						}
						if (columnType.contains("decimal") || columnType.contains("float")
								|| columnType.contains("double") || columnType.contains("integer")
								|| columnType.contains("longtext")) {
							oMap.put("cloumsType", "Long");
						}
						if (columnType.contains("date") || columnType.contains("datetime")) {
							oMap.put("cloumsType", "Date");
							cloums_top += " @Temporal(TemporalType.TIMESTAMP) ";
						}
						if (columnType.contains("int")) {
							oMap.put("cloumsType", "Integer");
						}

						cloums_top += "@Column( name = \"" + columnName + "\" ";
						if (columnName.equals(templateParams.getPkColumn().toUpperCase())) {
							cloums_top += ", unique = true ";
						}

						if (IsNullAble.equals("NO")) {
							cloums_top += " ,nullable = false ";
						}

						// 判断是否唯一性约束
						// if (isNullList.contains(key.equals("COLUMNTYPE"))) {
						// cloums_top += " , unique = true ";
						// }

						// 长度
						if (!columnType.contains("decimal")) {
							cloums_top += " , length = " + dataLength + " ";
						} else {
							// NUMBER 类型才有
							cloums_top += " , precision = " + dataLength + ", scale = 0 ";
						}

						cloums_top += " )";

						oMap.put("cloums_top", cloums_top);

					}

				}
				clList.add(oMap);// 添加到集合
			}
		}

		if (clList != null && clList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("functionComment", templateParams.getFunctionComment());// 功能说明
			data.put("className", templateParams.getClassNames());// 类名称
			data.put("cloums", clList);// 属性
			data.put("date", PbUtils.getCurrentDate());// 日期
			// 作者
			if (!PbUtils.isEmpty(templateParams.getAuthor())) {
				data.put("author", templateParams.getAuthor());
			}
			data.put("Table_NAME", templateParams.getTableName());// 表名称
			// 模板名称
			String ftlName = "/LayUIFtl/Model.ftl";

			// 生成文件的路径和名称
			// String fileName = templateParams.getClassPath() + "/" +
			// templateParams.getClassNames() + ".java";
			String fileName = templateParams.getClassPath() + "/TenDir-entity/src/main/java/com/intflag/tendir/entity/"
					+ templateParams.getClassNames() + ".java";
			TemplateHelp.creatTemplate(data, ftlName, fileName, request);
		}
	}

	/**
	 * 创建editjsp和addJsp的公用方法
	 * 
	 * @param ftlName
	 *            例如：AddJsp.ftl
	 * @param templateParams
	 * @param filePath
	 *            Id名称，例如sys_menuInf_MenuInfList
	 * @param fileName
	 *            文件生成的路径 例如 c://Add.jsp
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void AddAndEditPubMeth(String ftlName, TemplateParams templateParams, String filePath, String fileName,
			HttpServletRequest request) throws IOException, TemplateException {
		List<Map> list = getListMap(templateParams.getTableName());
		List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> datesList = new ArrayList<Map<String, Object>>();

		String entityId = "";
		String tableId = "";
		boolean flag = false;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> oMap = new HashMap<String, Object>();
				// 遍历list
				Map<String, Object> map = list.get(i);

				// 遍历map
				for (String key : map.keySet()) {

					// 列名称
					if (key.equals("COLUMNNAME")) {
						String columnName = map.get(key).toString();// 列名称
						String reStr = PbUtils.strRelplacetoLowerCase(map.get(key).toString());// 列名称，首字母小写，去下划线
						oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
						oMap.put("UpUmnName", reStr);
						// 判断是否为主键
						if ((columnName.toUpperCase()).equals(templateParams.getPkColumn().toUpperCase())) {
							entityId = reStr;
							tableId = columnName;
							flag = true;
							continue;
						}
					}
					/////
					// 注释
					if (key.equals("COLUMNCOMMENT")) {
						String reStr = PbUtils.strRelplacetoLowerCase(map.get("COLUMNNAME").toString());// 列名称，首字母小写，去下划线
						oMap.put("columnComment", map.get(key) == null ? reStr : map.get(key));// 注释
					}

					// 设置列长度,既maxlength
					if (key.equals("COLUMNTYPE")) {
						String columnType = map.get(key).toString();

						if (columnType.contains("varchar") || columnType.contains("char")) {
							// 设置列长度,既maxlength
							if (map.get("DATALENGTH") != null) {
								oMap.put("maxlength", (Integer.parseInt(map.get("DATALENGTH").toString())));
							}

						} else {
							// num类型,长度为2
							oMap.put("maxlength", 2);
						}
					}

					// 是否必填
					if (map.get("ISNULLABLE") != null) {
						oMap.put("IsNullAble", map.get("ISNULLABLE").toString().equals("NO") ? "NO" : "YES");
					}

					// 列类型
					if (key.equals("COLUMNTYPE")) {
						oMap.put("IsDate", "NO");
						String columnType = map.get("COLUMNTYPE").toString();// 列类型
						if (columnType.contains("timestamp") || columnType.contains("date")
								|| columnType.contains("datetime")) {
							oMap.put("IsDate", "YES");
							Map<String, Object> dateMap = new HashMap<String, Object>();
							String reStr = PbUtils.strRelplacetoLowerCase(map.get("COLUMNNAME").toString());// 列名称，首字母小写，去下划线
							dateMap.put("id", reStr);// ID
							datesList.add(dateMap);
						}
					}
				}
				if (flag) {
					flag = !flag;
				} else {
					clList.add(oMap);// 添加到集合
				}
			}
		}

		if (clList != null && clList.size() > 0) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("rootPath", TenDirUtils.getRootPath());// 类名称
			data.put("classNameUp", templateParams.getClassNames());// 类名称
			data.put("classNameLow", (templateParams.getClassNames()).toLowerCase());// 类名称
			data.put("cloums", clList);// 属性
			data.put("entityId", entityId);// 实体id
			data.put("EntityId", PbUtils.fristStrToUpperCase(entityId));// 实体id
			data.put("tableId", tableId);// 数据库id
			data.put("datesList", datesList);// 时间空间集合
			data.put("action", PbUtils.fristStrToLowerCase(templateParams.getClassNames()));// action名称
			data.put("filePath", filePath);// Id名称，例如sys_menuInf_MenuInfList
			data.put("listPagePath",
					"app_" + templateParams.getClassNames() + "_" + templateParams.getClassNames() + "List");// search页面的listPagePath属性
			TemplateHelp.creatTemplate(data, ftlName, fileName, request);
		}
	}
}
