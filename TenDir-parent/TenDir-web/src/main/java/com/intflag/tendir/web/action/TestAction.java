package com.intflag.tendir.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.Test;
import com.intflag.tendir.service.ITestService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * 
 * @功能说明：测试API控制层
 * @作者： 刘国鑫QQ1598749808
 * @创建日期：2018-07-10
 * @版本号：V1.0
 */
@Controller
@Scope("prototype")
public class TestAction extends BaseAction<Test> {

	private static final long serialVersionUID = -6543872666144162330L;

	@Autowired
	private ITestService testService;

	/*
	 * 分页查询
	 */
	public String pageQuery() throws Exception {
		List<String> field = new ArrayList<String>();
		String[] arr = new String[]{};
		field.add("tname");
		field.add("tpwd");
		field.add("email");
		field.add("phone");
		field.add("address");
		testService.pageQuery(pageBean, keyWord, field.toArray(arr));
		this.java2Json(pageBean, new String[] {});
		saveLog("分页查询测试API", METHOD_SUCCESS, "操作成功");
		return NONE;
	}

	/*
	 * 新增测试API
	 */
	public String add() throws Exception {
		String methodName = "新增测试API";
		AjaxRes res = getAjaxRes();
		try {
			testService.add(model);
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
	 * 查询测试API
	 */
	public String find() throws Exception {
		Test test = testService.find(model);
		ServletActionContext.getRequest().setAttribute("test", test);
		return EDIT;
	}
	
	/*
	 * 修改测试API
	 */
	public String edit() throws Exception {
		String methodName = "修改测试API";
		AjaxRes res = getAjaxRes();
		try {
			testService.update(model);
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
	 * 批量删除测试API
	 */
	public String deleteBatch() throws Exception {
		String methodName = "批量删除测试API";
		AjaxRes res = getAjaxRes();
		try {
			testService.deleteBatch(modelIds);
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
