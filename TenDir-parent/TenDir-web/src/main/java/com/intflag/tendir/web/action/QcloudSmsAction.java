package com.intflag.tendir.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.QcloudSms;
import com.intflag.tendir.service.IQcloudSmsService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月25日 上午11:19:47
 * @Description
 * @version V1.0
 */
@Controller
@Scope("prototype")
public class QcloudSmsAction extends BaseAction<QcloudSms> {

	private static final long serialVersionUID = 4841990282487124074L;

	@Autowired
	private IQcloudSmsService qcloudSmsService;

	/*
	 * 分页查询
	 */
	@RequiresPermissions("qcloudSmsAction_pageQuery")
	public String pageQuery() throws Exception {
		qcloudSmsService.pageQuery(pageBean, keyWord, new String[] { "appKey", "address", "content", "description" });
		this.java2Json(pageBean, new String[] {});
		saveLog("分页查询短信管理", METHOD_SUCCESS, "操作成功");
		return NONE;
	}
	
	/**
	 * 短信发送
	 */
	public String send() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			qcloudSmsService.add(model);
			res.setSucceedMsg("发送成功");
			saveLog("发送短信", METHOD_SUCCESS, "发送成功");
		} catch (Exception e) {
			res.setFailMsg("发送失败");
			saveLog("发送短信", METHOD_FAIL, "发送失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
