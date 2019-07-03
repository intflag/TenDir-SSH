package com.intflag.tendir.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.${Action};
import com.intflag.tendir.service.I${Action}Service;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * 
 * @功能说明：${functionComment}控制层
 * @作者： ${author}
 * @创建日期：${date}
 * @版本号：V1.0
 */
@Controller
@Scope("prototype")
public class ${Action}Action extends BaseAction<${Action}> {

	private static final long serialVersionUID = -6543872666144162330L;

	@Autowired
	private I${Action}Service ${action}Service;

	/*
	 * 分页查询
	 */
	public String pageQuery() throws Exception {
		List<String> field = new ArrayList<String>();
		String[] arr = new String[]{};
		<#list cloums as c>
		field.add("${c.columnName}");
		</#list>	
		${action}Service.pageQuery(pageBean, keyWord, field.toArray(arr));
		this.java2Json(pageBean, new String[] {});
		saveLog("分页查询${functionComment}", METHOD_SUCCESS, "操作成功");
		return NONE;
	}

	/*
	 * 新增${functionComment}
	 */
	public String add() throws Exception {
		String methodName = "新增${functionComment}";
		AjaxRes res = getAjaxRes();
		try {
			${action}Service.add(model);
			res.setSucceedMsg(Const.SAVE_SUCCEED);
			saveLog(methodName, METHOD_SUCCESS, "保存成功");
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			saveLog(methodName, METHOD_FAIL, "保存失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
	
	/*
	 * 查询${functionComment}
	 */
	public String find() throws Exception {
		${Action} ${action} = ${action}Service.find(model);
		ServletActionContext.getRequest().setAttribute("${action}", ${action});
		return EDIT;
	}
	
	/*
	 * 修改${functionComment}
	 */
	public String edit() throws Exception {
		String methodName = "修改${functionComment}";
		AjaxRes res = getAjaxRes();
		try {
			${action}Service.update(model);
			res.setSucceedMsg(Const.UPDATE_SUCCEED);
			saveLog(methodName, METHOD_SUCCESS, "修改成功");
		} catch (Exception e) {
			res.setFailMsg(Const.UPDATE_FAIL);
			saveLog(methodName, METHOD_FAIL, "修改失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
	
	/*
	 * 批量删除${functionComment}
	 */
	public String deleteBatch() throws Exception {
		String methodName = "批量删除${functionComment}";
		AjaxRes res = getAjaxRes();
		try {
			${action}Service.deleteBatch(modelIds);
			res.setSucceedMsg(Const.DEL_SUCCEED);
			saveLog(methodName, METHOD_SUCCESS, "删除成功");
		} catch (Exception e) {
			res.setFailMsg(Const.DEL_FAIL);
			saveLog(methodName, METHOD_FAIL, "删除失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
