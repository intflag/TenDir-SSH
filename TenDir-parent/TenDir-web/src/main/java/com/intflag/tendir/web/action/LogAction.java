package com.intflag.tendir.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.Log;
import com.intflag.tendir.service.ILogService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月17日 上午10:53:37
 * @Description 日志控制层
 * @version V1.0
 */
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 8693202562526625752L;

	@Autowired
	private ILogService logService;

	/*
	 * 分页查询
	 */
	@RequiresPermissions("logAction_pageQuery")
	public String pageQuery() throws Exception {
		logService.pageQuery(pageBean,keyWord,new String[]{"username","method","ip","address"});
		this.java2Json(pageBean, new String[] {});
		return NONE;
	}
	
	/*
	 * 批量删除日志
	 */
	@RequiresPermissions("logAction_deleteBatch")
	public String deleteBatch() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			logService.deleteBatch(modelIds);
			res.setSucceedMsg(Const.DEL_SUCCEED);
			saveLog("批量删除日志", METHOD_SUCCESS, "删除成功");
		} catch (Exception e) {
			res.setFailMsg(Const.DEL_FAIL);
			saveLog("批量删除日志", METHOD_FAIL, "删除失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
