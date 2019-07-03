package com.intflag.tendir.web.action;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.intflag.tendir.entity.Role;
import com.intflag.tendir.entity.User;
import com.intflag.tendir.service.IUserService;
import com.intflag.tendir.utils.AjaxRes;
import com.intflag.tendir.utils.Const;
import com.intflag.tendir.utils.MD5Utils;
import com.intflag.tendir.web.action.base.BaseAction;

/**
 * @author 刘国鑫
 * @date 2018年3月26日 下午12:40:00
 * @Description 用户Action
 * @version V1.0
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = -2303535462850606443L;

	private String checkCode;

	@Autowired
	private IUserService userService;

	/*
	 * 用户登录
	 */
	public String login_bak() throws Exception {
		// 校验验证码
		String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkCode) && validateCode.equals(checkCode)) {
			User user = userService.login(model);
			if (user != null) {
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return HOME;
			} else {
				this.addActionError("用户名或密码输入错误！");
				return LOGIN;
			}
		} else {
			this.addActionError("验证码输入错误！");
			return LOGIN;
		}
	}

	/*
	 * 用户登录，适用shiro进行权限认证
	 */
	public String login() throws Exception {
		String methodName = "用户登录";
		// 校验验证码
		String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkCode) && validateCode.equals(checkCode)) {
			// 适用shiro进行权限认证
			Subject subject = SecurityUtils.getSubject();
			// 创建用户名密码令牌
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),
					MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);
			} catch (UnknownAccountException e) {
				this.addActionError("账户不存在！");
				saveLog(model.getUsername(), methodName, METHOD_FAIL, "账户不存在");
				e.printStackTrace();
				return LOGIN;
			} catch (IncorrectCredentialsException e) {
				this.addActionError("口令错误！");
				saveLog(model.getUsername(), methodName, METHOD_FAIL, "口令错误");
				e.printStackTrace();
				return LOGIN;
			} catch (Exception e) {
				this.addActionError("异常错误！");
				saveLog(model.getUsername(), methodName, METHOD_FAIL, "异常错误");
				e.printStackTrace();
				return LOGIN;
			}
			// 认证通过，将用户放入session，跳转到主页
			User user = (User) subject.getPrincipal();
			saveLog(model.getUsername(), methodName, METHOD_SUCCESS, "登录成功");
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		} else {
			this.addActionError("验证码输入错误！");
			saveLog(model.getUsername(), methodName, METHOD_FAIL, "验证码输入错误");
			return LOGIN;
		}
	}

	/*
	 * 锁屏解锁
	 */
	public String unlock() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			User user = userService.unlock(model);
			if (user != null) {
				res.setSucceedMsg(Const.UNLOCK_SUCCEED);
			} else {
				res.setFailMsg(Const.UNLOCK_FAIL);
			}
		} catch (Exception e) {
			res.setFailMsg(Const.UNLOCK_FAIL);
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 用户退出
	 */
	public String logout() throws Exception {
		saveLog("用户退出", METHOD_SUCCESS, "操作成功");
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

	private String roleIds;

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	/*
	 * 新增用户
	 */
	@RequiresPermissions("userAction_add")
	public String add() throws Exception {
		String methodName = "新增用户";
		AjaxRes res = getAjaxRes();
		try {
			User user = userService.findByName(model.getUsername());
			if (user == null) {
				userService.add(model, roleIds);
				res.setSucceedMsg(Const.SAVE_SUCCEED);
				saveLog(methodName, METHOD_SUCCESS, "保存成功");
			} else {
				res.setFailMsg(Const.SAVE_FAIL + "，该用户已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			saveLog(methodName, METHOD_FAIL, "保存失败");
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 根据用户名查找
	 */
	public String findByName() throws Exception {
		AjaxRes res = getAjaxRes();
		try {
			User user = userService.findByName(model.getUsername());
			if (user == null) {
				res.setSucceedMsg("该用户可以使用");
			} else {
				res.setFailMsg("该用户已存在");
			}
		} catch (Exception e) {
			res.setFailMsg(Const.SAVE_FAIL);
			e.printStackTrace();
		}
		java2Json(res);
		return NONE;
	}

	/*
	 * 修改用户
	 */
	@RequiresPermissions("userAction_edit")
	public String edit() throws Exception {
		String methodName = "修改用户";
		AjaxRes res = getAjaxRes();
		try {
			userService.update(model, roleIds);
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
	 * 批量删除用户
	 */
	@RequiresPermissions("userAction_deleteBatch")
	public String deleteBatch() throws Exception {
		String methodName = "批量删除用户";
		AjaxRes res = getAjaxRes();
		try {
			userService.deleteBatch(modelIds);
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

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/*
	 * 分页查询
	 */
	@RequiresPermissions("userAction_pageQuery")
	public String pageQuery() throws Exception {
		userService.pageQuery(pageBean,keyWord,new String[]{"username","nickname","email","telephone"});
		this.java2Json(pageBean, new String[] { "resources", "users" });
		saveLog("分页查询用户", METHOD_SUCCESS, "操作成功");
		return NONE;
	}

	/*
	 * 查询用户
	 */
	@SuppressWarnings("rawtypes")
	public String find() throws Exception {
		User user = userService.find(model);
		Set roles = user.getRoles();
		StringBuffer roleIds = new StringBuffer();
		if (roles.size() > 0) {
			for (Object obj : roles) {
				Role role = (Role) obj;
				roleIds.append(role.getRoleId() + ",");
			}
		}
		ServletActionContext.getRequest().setAttribute("user", user);
		ServletActionContext.getRequest().setAttribute("roleIds", roleIds);
		return EDIT;
	}
}
