package com.intflag.tendir.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.Region;
import com.intflag.tendir.service.IRegionService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月24日 上午10:30:22
 * @Description 全国地区管理
 * @version V1.0
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private static final long serialVersionUID = 985749701440179000L;

	@Autowired
	private IRegionService regionService;

	/*
	 * 添加地区
	 */
	@RequiresPermissions("regionAction_add")
	public String add() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Region region = regionService.findByRegionName(model.getRegionName());
			if (region == null) {
				regionService.add(model);
				res.setSucceedMsg(Const.SAVE_SUCCEED);
				saveLog("添加地区", METHOD_SUCCESS, "保存成功");
			} else {
				res.setFailMsg(Const.SAVE_FAIL + "，该菜单已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			saveLog("添加地区", METHOD_FAIL, "保存失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 修改地区
	 */
	@RequiresPermissions("regionAction_edit")
	public String edit() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			regionService.update(model);
			res.setSucceedMsg(Const.UPDATE_SUCCEED);
			saveLog("修改地区", METHOD_SUCCESS, "修改成功");
		} catch (Exception e) {
			res.setFailMsg(Const.UPDATE_FAIL);
			saveLog("修改地区", METHOD_FAIL, "修改失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 查找
	 */
	public String find() throws Exception {
		Region region = regionService.find(model);
		ServletActionContext.getRequest().setAttribute("region", region);
		return EDIT;
	}

	/*
	 * 分页查询
	 */
	@RequiresPermissions("regionAction_pageQuery")
	public String pageQuery() throws Exception {
		regionService.pageQuery(pageBean, keyWord, new String[] { "regionName", "regionFullName" });
		this.java2Json(pageBean, new String[] { "region", "children" });
		saveLog("分页查询全国地区", METHOD_SUCCESS, "操作成功");
		return NONE;
	}

	/*
	 * 查找所有
	 */
	public String findAll() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			List<Region> list = regionService.findByLevel(1);
			res.setSucceedMsg(Const.FIND_SUCCEED);
			res.setObj(list);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		java2Json(res, new String[] { "region" });
		return NONE;
	}

	/*
	 * 根据菜单名查找
	 */
	public String findByRegionName() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Region region = regionService.findByRegionName(model.getRegionName());
			if (region == null) {
				res.setSucceedMsg("该地区名可以使用");
			} else {
				res.setFailMsg("该地区名已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 批量删除地区
	 */
	@RequiresPermissions("regionAction_deleteBatch")
	public String deleteBatch() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			regionService.deleteBatch(modelIds);
			res.setSucceedMsg(Const.DEL_SUCCEED);
			saveLog("批量删除地区", METHOD_SUCCESS, "删除成功");
		} catch (Exception e) {
			res.setFailMsg(Const.DEL_FAIL);
			saveLog("批量删除地区", METHOD_FAIL, "删除失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
