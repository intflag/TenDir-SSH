package com.intflag.tendir.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.intflag.tendir.dao.IResourceDao;
import com.intflag.tendir.dao.IUserDao;
import com.intflag.tendir.entity.Resource;
import com.intflag.tendir.entity.User;

/**
 * @author 刘国鑫
 * @date 2018年3月30日 上午10:18:31
 * @Description 自定义realm，用于认证和授权
 * @version V1.0
 */
public class TenDirRealm extends AuthorizingRealm {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IResourceDao resourceDao;

	/*
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("自定义realm中的认证方法执行了。。。。");
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		// 获得页面输入的用户名
		String username = usernamePasswordToken.getUsername();
		// 根据用户名查询用户
		User user = userDao.findByUsername(username);
		if (user == null) {
			// 用户不存在
			return null;
		}
		// 框架负责比对数据库中的密码是否与页面输入的一致
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

	/*
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录对象
		//User user = (User) SecurityUtils.getSubject().getPrincipal();
		User user = (User) principalCollection.getPrimaryPrincipal();
		//根据当前登录用户查询数据库，获取实际对应的权限
		List<Resource> list = null;
		if (user.getUsername().equals("Admin")) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Resource.class);
			//超级管理员内置用户，查询所有权限数据
			list = resourceDao.findByCriteria(detachedCriteria );
		} else {
			list = resourceDao.findListByUserId(user.getUserId());
		}
		
		for (Resource resource : list) {
			info.addStringPermission(resource.getRescode());
		}
		return info;
	}

}
