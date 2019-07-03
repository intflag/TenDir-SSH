package com.intflag.tendir.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.MailLog;
import com.intflag.tendir.service.IMailLogService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.web.action.base.BaseAction;

/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年5月24日 下午8:59:21
* @Description 邮件管理
* @version V1.0
*/
@Controller
@Scope("prototype")
public class MailLogAction extends BaseAction<MailLog> {

	private static final long serialVersionUID = -4339744212030476574L;

	@Autowired
	private IMailLogService mailLogService;
	
	/*
	 * 分页查询
	 */
	@RequiresPermissions("mailAction_pageQuery")
	public String pageQuery() throws Exception {
		mailLogService.pageQuery(pageBean,keyWord,new String[]{"smtpServer","sendTitle","sendAddress","sendContent","description"});
		this.java2Json(pageBean, new String[] {});
		saveLog("分页查询邮件管理", METHOD_SUCCESS, "操作成功");
		return NONE;
	}
	
	/**
	 * 邮件发送
	 */
	public String send() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			mailLogService.add(model);
			res.setSucceedMsg("发送成功");
			saveLog("发送邮件", METHOD_SUCCESS, "发送成功");
		} catch (Exception e) {
			res.setFailMsg("发送失败");
			saveLog("发送邮件", METHOD_FAIL, "发送失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
