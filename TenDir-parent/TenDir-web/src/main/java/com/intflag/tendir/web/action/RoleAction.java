package com.intflag.tendir.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.Role;
import com.intflag.tendir.service.IRoleService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年4月4日 上午8:41:41
 * @Description 角色控制层
 * @version V1.0
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 8616377515077182220L;

	@Autowired
	private IRoleService roleService;

	private String resourceIds;

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	/*
	 * 新增角色
	 */
	@RequiresPermissions("roleAction_add")
	public String add() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Role role = roleService.findByRoleName(model.getRolename());
			if (role == null) {
				roleService.add(model, resourceIds);
				res.setSucceedMsg(Const.SAVE_SUCCEED);
				saveLog("新增角色", METHOD_SUCCESS, "保存成功");
			} else {
				res.setFailMsg(Const.SAVE_FAIL+"，该角色名已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			saveLog("新增角色", METHOD_FAIL, "保存失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
	
	/*
	 * 根据角色名查找
	 */
	public String findByRoleName() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Role role = roleService.findByRoleName(model.getRolename());
			if (role == null) {
				res.setSucceedMsg("该角色名可以使用");
			} else {
				res.setFailMsg("该角色名已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 分页查询
	 */
	@RequiresPermissions("roleAction_pageQuery")
	public String pageQuery() throws Exception {
		roleService.pageQuery(pageBean,keyWord,new String[]{"rolename","description"});
		this.java2Json(pageBean, new String[] { "resources", "users" });
		saveLog("分页查询角色", METHOD_SUCCESS, "操作成功");
		return NONE;
	}

	/*
	 * 查找
	 */
	public String find() throws Exception {
		Role role = roleService.find(model);
		ServletActionContext.getRequest().setAttribute("role", role);
		return EDIT;
	}

	/*
	 * 查找
	 */
	public String findAjax() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Role role = roleService.find(model);
			res.setSucceed(role, Const.FIND_SUCCEED);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		this.java2Json(res, new String[] { "parentResource", "users", "roles", "children" });
		return NONE;
	}
	/*
	 * 查找所有
	 */
	public String findAll() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			List<Role> list = roleService.findAll();
			res.setSucceed(list, Const.FIND_SUCCEED);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		this.java2Json(res, new String[] { "parentResource", "users", "roles", "children" });
		return NONE;
	}

	/*
	 * 修改角色
	 */
	@RequiresPermissions("roleAction_edit")
	public String edit() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			roleService.update(model, resourceIds);
			res.setSucceedMsg(Const.UPDATE_SUCCEED);
			saveLog("修改角色", METHOD_SUCCESS, "修改成功");
		} catch (Exception e) {
			res.setFailMsg(Const.UPDATE_FAIL);
			saveLog("修改角色", METHOD_FAIL, "修改失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
	
	/*
	 * 批量删除角色
	 */
	@RequiresPermissions("roleAction_deleteBatch")
	public String deleteBatch() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			roleService.deleteBatch(modelIds);
			res.setSucceedMsg(Const.DEL_SUCCEED);
			saveLog("批量删除角色", METHOD_SUCCESS, "删除成功");
		} catch (Exception e) {
			res.setFailMsg(Const.DEL_FAIL);
			saveLog("批量删除角色", METHOD_FAIL, "删除失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
