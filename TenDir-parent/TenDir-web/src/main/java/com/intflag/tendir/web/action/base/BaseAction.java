package com.intflag.tendir.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.intflag.tendir.entity.Log;
import com.intflag.tendir.service.ILogService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.PageBean;
import com.intflag.tendir.utils.TenDirUtils;
import com.intflag.tendir.webservice.ArrayOfString;
import com.intflag.tendir.webservice.IpAddressSearchWebServiceSoap;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @author 刘国鑫
 * @date 2018年3月26日 下午12:17:37
 * @Description 表现层通用实现
 * @version V1.0
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Autowired
	private ILogService logService;
	
	@Autowired
	private IpAddressSearchWebServiceSoap soap;

	private static final long serialVersionUID = -6581187756389250533L;

	protected T model;

	protected String modelIds;

	public void setModelIds(String modelIds) {
		this.modelIds = modelIds;
	}
	
	protected String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public static final String HOME = "home";
	public static final String EDIT = "edit";
	public static final String METHOD_SUCCESS = "1";
	public static final String METHOD_FAIL = "2";

	protected PageBean pageBean = new PageBean();

	// 创建离线查询对象
	DetachedCriteria detachedCriteria = null;

	public void setCurrentPage(int currentPage) {
		pageBean.setCurrentPage(currentPage);
	}

	public void setPageSize(int pageSize) {
		pageBean.setPageSize(pageSize);
	}

	// 获取结果集对象
	public AjaxRes getAjaxRes() {
		return new AjaxRes();
	}

	/**
	 * 将指定java对象转为json，输出到前端
	 * 
	 * @param object
	 * @param excludes
	 */
	public void java2Json(Object object, String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		// 指定转换时不包含那些属性
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(object, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转为json
	 * 
	 * @param object
	 */
	public void java2Json(Object object) {
		String json = JSONObject.fromObject(object).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 在构造方法中动态获取实体类型，通过反射创建对象
	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得BaseAction上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		// 通过反射创建对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存日志
	 */
	public void saveLog(String username, String methodName, String flag, String description) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = TenDirUtils.getClientIpAddr(request );
		ArrayOfString countryCityByIp = soap.getCountryCityByIp(ip);
		String address = countryCityByIp.getString().get(1);
		TenDirUtils.getSession().setAttribute("currentUserIP", ip);
		TenDirUtils.getSession().setAttribute("currentUserAddress", address);
		Log log = new Log(username, methodName, ip, address, flag, 0, new Timestamp(System.currentTimeMillis()),
				description);
		logService.save(log);
	}
	/**
	 * 保存日志
	 */
	public void saveLog(String methodName, String flag, String description) {
		String ip = (String) TenDirUtils.getSession().getAttribute("currentUserIP");
		String address = (String) TenDirUtils.getSession().getAttribute("currentUserAddress");
		String username = TenDirUtils.getLoginUser().getUsername();
		Log log = new Log(username , methodName, ip, address, flag, 0, new Timestamp(System.currentTimeMillis()),
				description);
		logService.save(log);
	}

	@Override
	public T getModel() {
		return model;
	}

}
