package com.intflag.tendir.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.Resource;
import com.intflag.tendir.service.IResourceService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.utils.MenuTreeUtil;
import com.intflag.tendir.web.action.base.BaseAction;

/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年3月31日 下午4:02:35
* @Description 资源菜单管理
* @version V1.0
*/
@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource> {

	private static final long serialVersionUID = 1412748511377305869L;
	
	@Autowired
	private IResourceService resourceService; 

	/*
	 * 添加资源菜单
	 */
	@RequiresPermissions("resourceAction_add")
	public String add() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Resource resource = resourceService.findByResName(model.getResname());
			if (resource == null) {
				resourceService.add(model);
				res.setSucceedMsg(Const.SAVE_SUCCEED);
				saveLog("添加资源菜单", METHOD_SUCCESS, "保存成功");
			} else {
				res.setFailMsg(Const.SAVE_FAIL+"，该菜单已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			saveLog("添加资源菜单", METHOD_FAIL, "保存失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
	
	/*
	 * 根据菜单名查找
	 */
	public String findByResName() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			Resource resource = resourceService.findByResName(model.getResname());
			if (resource == null) {
				res.setSucceedMsg("该菜单名可以使用");
			} else {
				res.setFailMsg("该菜单名已存在");
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
	@RequiresPermissions("resourceAction_pageQuery")
	public String pageQuery() throws Exception {
		resourceService.pageQuery(pageBean,keyWord,new String[]{"resname","resurl","rescode","icon","description"});
		this.java2Json(pageBean, new String[] {"roles","children"});
		saveLog("分页查询资源菜单", METHOD_SUCCESS, "操作成功");
		return NONE;
	}
	
	/*
	 * 查找所有
	 */
	public String findAll() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			List<Resource> list = resourceService.findAll();
			res.setSucceedMsg(Const.FIND_SUCCEED);
			res.setObj(list);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		java2Json(res, new String[]{"parentResource","roles"});
		return NONE;
	}
	/*
	 * 查找
	 */
	public String find() throws Exception {
		Resource resource = resourceService.find(model);
		ServletActionContext.getRequest().setAttribute("resource", resource);
		return EDIT;
	}
	/*
	 * 修改资源菜单
	 */
	@RequiresPermissions("resourceAction_edit")
	public String edit() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			resourceService.update(model);
			res.setSucceedMsg(Const.UPDATE_SUCCEED);
			saveLog("修改资源菜单", METHOD_SUCCESS, "修改成功");
		} catch (Exception e) {
			res.setFailMsg(Const.UPDATE_FAIL);
			saveLog("修改资源菜单", METHOD_FAIL, "修改失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
	
	/*
	 * 查找菜单
	 */
	public String findMenu() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			List<Resource> list = resourceService.findMenu();
			List<Resource> nemuTree = MenuTreeUtil.GetTree(list);
			res.setObj(nemuTree);
			res.setSucceedMsg(Const.FIND_SUCCEED);
		} catch (Exception e) {
			res.setFailMsg(Const.FIND_FAIL);
			e.printStackTrace();
		}
		java2Json(res, new String[]{"parentResource","roles"});
		return NONE;
	}
	
	/*
	 * 批量删除资源菜单
	 */
	@RequiresPermissions("resourceAction_deleteBatch")
	public String deleteBatch() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			resourceService.deleteBatch(modelIds);
			res.setSucceedMsg(Const.DEL_SUCCEED);
			saveLog("批量删除资源菜单", METHOD_SUCCESS, "删除成功");
		} catch (Exception e) {
			res.setFailMsg(Const.DEL_FAIL);
			saveLog("批量删除资源菜单", METHOD_FAIL, "删除失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}
}
