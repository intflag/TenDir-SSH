package com.intflag.tendir.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.TableFields;
import com.intflag.tendir.entity.TemplateParams;
import com.intflag.tendir.service.ICodeGeneratorService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.utils.TenDirUtils;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年6月12日 下午6:09:06
 * @Description 代码生成器管理
 * @version V1.0
 */
@Controller
@Scope("prototype")
public class CodeGeneratorAction extends BaseAction<TemplateParams> {

	private static final long serialVersionUID = 19405323578193850L;

	@Autowired
	private ICodeGeneratorService codeGeneratorService;

	/*
	 * 查找所有表
	 */
	public String findAllTable() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			List<TemplateParams> list = codeGeneratorService.findAllTable();
			res.setSucceedMsg(Const.FIND_SUCCEED);
			res.setObj(list);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		java2Json(res, new String[] {});
		return NONE;
	}

	/*
	 * 根据表名查找该表所有字段
	 */
	public String findFieldByTableName() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			List<TableFields> list = codeGeneratorService.findFieldByTableName(model);
			res.setSucceedMsg(Const.FIND_SUCCEED);
			res.setObj(list);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		java2Json(res, new String[] {});
		return NONE;
	}

	/*
	 * 生成代码
	 */
	// @RequiresPermissions("logAction_deleteBatch")
	public String createCode() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			boolean flag = codeGeneratorService.createCode(model, TenDirUtils.getSession(), TenDirUtils.getRequest());
			if (flag) {
				String codeStrutsXml = (String) TenDirUtils.getSession().getAttribute("codeStrutsXml");
				res.setObj(codeStrutsXml);
				res.setSucceedMsg("生成成功");
				saveLog("代码生成", METHOD_SUCCESS, "生成成功");
			} else {
				res.setFailMsg("生成失败");
				saveLog("代码生成", METHOD_FAIL, "生成失败");
			}
		} catch (Exception e) {
			res.setFailMsg("生成失败");
			saveLog("代码生成", METHOD_FAIL, "生成失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
